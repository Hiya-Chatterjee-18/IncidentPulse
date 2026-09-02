package com.incident.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    private String id;
    private String title;
    private String category;
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String impact;
    private String urgency;
    private String affectedSystem;
    private String location;

    private String reporterId;
    private String reporterName;
    private String reporterEmail;
    private String reporterDepartment;

    private String assigneeId;
    private String assigneeName;
    private String assigneeSpecialty;
    private String assigneeAvatar;

    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();

    private Instant slaResponseDeadline;
    private Instant slaResolveDeadline;
    private Boolean slaResponseMet;
    private Boolean slaResolveMet;

    @Transient
    private Resolution resolution;

    @Transient
    private List<Comment> comments = new ArrayList<>();

    @Transient
    private List<AuditLog> history = new ArrayList<>();

    public Incident() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getImpact() { return impact; }
    public void setImpact(String impact) { this.impact = impact; }
    public String getUrgency() { return urgency; }
    public void setUrgency(String urgency) { this.urgency = urgency; }
    public String getAffectedSystem() { return affectedSystem; }
    public void setAffectedSystem(String affectedSystem) { this.affectedSystem = affectedSystem; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getReporterId() { return reporterId; }
    public void setReporterId(String reporterId) { this.reporterId = reporterId; }
    public String getReporterName() { return reporterName; }
    public void setReporterName(String reporterName) { this.reporterName = reporterName; }
    public String getReporterEmail() { return reporterEmail; }
    public void setReporterEmail(String reporterEmail) { this.reporterEmail = reporterEmail; }
    public String getReporterDepartment() { return reporterDepartment; }
    public void setReporterDepartment(String reporterDepartment) { this.reporterDepartment = reporterDepartment; }
    public String getAssigneeId() { return assigneeId; }
    public void setAssigneeId(String assigneeId) { this.assigneeId = assigneeId; }
    public String getAssigneeName() { return assigneeName; }
    public void setAssigneeName(String assigneeName) { this.assigneeName = assigneeName; }
    public String getAssigneeSpecialty() { return assigneeSpecialty; }
    public void setAssigneeSpecialty(String assigneeSpecialty) { this.assigneeSpecialty = assigneeSpecialty; }
    public String getAssigneeAvatar() { return assigneeAvatar; }
    public void setAssigneeAvatar(String assigneeAvatar) { this.assigneeAvatar = assigneeAvatar; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
    public Instant getSlaResponseDeadline() { return slaResponseDeadline; }
    public void setSlaResponseDeadline(Instant slaResponseDeadline) { this.slaResponseDeadline = slaResponseDeadline; }
    public Instant getSlaResolveDeadline() { return slaResolveDeadline; }
    public void setSlaResolveDeadline(Instant slaResolveDeadline) { this.slaResolveDeadline = slaResolveDeadline; }
    public Boolean getSlaResponseMet() { return slaResponseMet; }
    public void setSlaResponseMet(Boolean slaResponseMet) { this.slaResponseMet = slaResponseMet; }
    public Boolean getSlaResolveMet() { return slaResolveMet; }
    public void setSlaResolveMet(Boolean slaResolveMet) { this.slaResolveMet = slaResolveMet; }
    public Resolution getResolution() { return resolution; }
    public void setResolution(Resolution resolution) { this.resolution = resolution; }
    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }
    public List<AuditLog> getHistory() { return history; }
    public void setHistory(List<AuditLog> history) { this.history = history; }
}
