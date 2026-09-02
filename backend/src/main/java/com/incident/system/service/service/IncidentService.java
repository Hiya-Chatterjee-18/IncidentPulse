package com.incident.system.service;

import com.incident.system.dto.CommentRequest;
import com.incident.system.dto.CreateIncidentRequest;
import com.incident.system.dto.ResolutionRequest;
import com.incident.system.event.KafkaIncidentEventProducer;
import com.incident.system.event.RabbitMqEventPublisher;
import com.incident.system.exception.ResourceNotFoundException;
import com.incident.system.model.*;
import com.incident.system.repository.IncidentRepository;
import com.incident.system.repository.KnowledgeBaseRepository;
import com.incident.system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final UserRepository userRepository;
    private final KnowledgeBaseRepository kbRepository;
    private final IntelligentPriorityEngine priorityEngine;
    private final DuplicateClassifier duplicateClassifier;
    private final KafkaIncidentEventProducer kafkaProducer;
    private final RabbitMqEventPublisher rabbitMqPublisher;

    public IncidentService(IncidentRepository incidentRepository, UserRepository userRepository, KnowledgeBaseRepository kbRepository, IntelligentPriorityEngine priorityEngine, DuplicateClassifier duplicateClassifier, KafkaIncidentEventProducer kafkaProducer, RabbitMqEventPublisher rabbitMqPublisher) {
        this.incidentRepository = incidentRepository;
        this.userRepository = userRepository;
        this.kbRepository = kbRepository;
        this.priorityEngine = priorityEngine;
        this.duplicateClassifier = duplicateClassifier;
        this.kafkaProducer = kafkaProducer;
        this.rabbitMqPublisher = rabbitMqPublisher;
    }

    public List<Incident> getAllIncidents(String status, String priority, String category) {
        List<Incident> list = incidentRepository.findAll();

        if (status != null && !status.equalsIgnoreCase("all")) {
            list = list.stream().filter(i -> i.getStatus().name().equalsIgnoreCase(status)).toList();
        }
        if (priority != null && !priority.equalsIgnoreCase("all")) {
            list = list.stream().filter(i -> i.getPriority().name().equalsIgnoreCase(priority)).toList();
        }
        if (category != null && !category.equalsIgnoreCase("all")) {
            list = list.stream().filter(i -> i.getCategory().equalsIgnoreCase(category)).toList();
        }

        return list;
    }

    public Incident getIncidentById(String id) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found with ID: " + id));
    }

    public Incident createIncident(CreateIncidentRequest req, String reporterEmail) {
        User reporter = userRepository.findByEmail(reporterEmail)
                .orElseGet(() -> userRepository.findAll().stream().findFirst().orElseThrow());

        Priority calculatedPriority = priorityEngine.classifyPriority(req.getTitle(), req.getDescription(), req.getUrgency(), req.getImpact());

        String incidentId = "INC-" + (Instant.now().getEpochSecond() % 100000);
        Instant now = Instant.now();

        Instant responseDeadline = now.plus(calculatedPriority.getResponseSlaHours(), ChronoUnit.HOURS);
        Instant resolveDeadline = now.plus(calculatedPriority.getResolveSlaHours(), ChronoUnit.HOURS);

        Incident incident = new Incident();
        incident.setId(incidentId);
        incident.setTitle(req.getTitle());
        incident.setCategory(req.getCategory());
        incident.setDescription(req.getDescription());
        incident.setPriority(calculatedPriority);
        incident.setStatus(Status.OPEN);
        incident.setImpact(req.getImpact() != null ? req.getImpact() : "Department");
        incident.setUrgency(req.getUrgency() != null ? req.getUrgency() : "Normal");
        incident.setAffectedSystem(req.getAffectedSystem() != null ? req.getAffectedSystem() : "General System");
        incident.setLocation(req.getLocation() != null ? req.getLocation() : "HQ");

        incident.setReporterId(reporter.getId());
        incident.setReporterName(reporter.getName());
        incident.setReporterEmail(reporter.getEmail());
        incident.setReporterDepartment(reporter.getDepartment());

        incident.setSlaResponseDeadline(responseDeadline);
        incident.setSlaResolveDeadline(resolveDeadline);

        incident.getHistory().add(new AuditLog(reporter.getName(), "Created Incident", "Status: OPEN, Priority: " + calculatedPriority.name()));

        Incident saved = incidentRepository.save(incident);

        // Emit Events
        kafkaProducer.publishLifecycleEvent("INCIDENT_CREATED", saved.getId());
        rabbitMqPublisher.publishIncidentEvent("INCIDENT_CREATED_TASK", saved.getId());

        return saved;
    }

    public Incident assignAgent(String id, String agentId, String actorName) {
        Incident incident = getIncidentById(id);
        User agent = userRepository.findById(agentId)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found with ID: " + agentId));

        Instant now = Instant.now();
        if (incident.getSlaResponseMet() == null) {
            incident.setSlaResponseMet(now.isBefore(incident.getSlaResponseDeadline()));
        }

        incident.setAssigneeId(agent.getId());
        incident.setAssigneeName(agent.getName());
        incident.setAssigneeSpecialty(agent.getSpecialty());
        incident.setAssigneeAvatar(agent.getAvatar());
        incident.setUpdatedAt(now);

        if (incident.getStatus() == Status.OPEN) {
            incident.setStatus(Status.ASSIGNED);
        }

        incident.getHistory().add(new AuditLog(actorName, "Assigned Agent", "Assigned to " + agent.getName()));
        Incident saved = incidentRepository.save(incident);

        kafkaProducer.publishLifecycleEvent("STATUS_CHANGED", saved.getId());
        return saved;
    }

    public Incident updateStatus(String id, Status newStatus, String actorName) {
        Incident incident = getIncidentById(id);
        Status oldStatus = incident.getStatus();
        Instant now = Instant.now();

        incident.setStatus(newStatus);
        incident.setUpdatedAt(now);
        incident.getHistory().add(new AuditLog(actorName, "Changed Status", oldStatus.name() + " ➔ " + newStatus.name()));

        Incident saved = incidentRepository.save(incident);
        kafkaProducer.publishLifecycleEvent("STATUS_CHANGED", saved.getId());
        return saved;
    }

    public Incident resolveIncident(String id, ResolutionRequest req, String actorName) {
        Incident incident = getIncidentById(id);
        Instant now = Instant.now();

        boolean resolveMet = now.isBefore(incident.getSlaResolveDeadline());
        incident.setSlaResolveMet(resolveMet);
        incident.setStatus(Status.RESOLVED);
        incident.setUpdatedAt(now);

        Resolution resolution = new Resolution(req.getRootCause(), req.getWorkaround(), req.getActionTaken(), actorName);
        incident.setResolution(resolution);
        incident.getHistory().add(new AuditLog(actorName, "Resolved Incident", "Action: " + req.getActionTaken()));

        if (req.isAddToKB()) {
            KnowledgeBaseArticle kb = new KnowledgeBaseArticle(
                    "kb-" + (new Random().nextInt(900) + 100),
                    "Resolution: " + incident.getTitle(),
                    incident.getCategory(),
                    req.getActionTaken(),
                    req.getRootCause(),
                    req.getActionTaken(),
                    List.of(incident.getCategory(), incident.getPriority().name())
            );
            kbRepository.save(kb);
        }

        Incident saved = incidentRepository.save(incident);
        kafkaProducer.publishLifecycleEvent("INCIDENT_RESOLVED", saved.getId());
        return saved;
    }

    public Incident addComment(String id, CommentRequest req, String authorName, String authorRole) {
        Incident incident = getIncidentById(id);
        Comment comment = new Comment("c_" + System.currentTimeMillis(), authorName, authorRole, req.getText(), req.isInternal());
        incident.getComments().add(comment);
        incident.setUpdatedAt(Instant.now());

        return incidentRepository.save(incident);
    }
}
