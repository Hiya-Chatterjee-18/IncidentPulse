package com.incident.system.controller;

import com.incident.system.dto.CommentRequest;
import com.incident.system.dto.CreateIncidentRequest;
import com.incident.system.dto.ResolutionRequest;
import com.incident.system.model.Incident;
import com.incident.system.model.Status;
import com.incident.system.service.IncidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/incidents")
@CrossOrigin(origins = "*")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(incidentService.getAllIncidents(status, priority, category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable String id) {
        return ResponseEntity.ok(incidentService.getIncidentById(id));
    }

    @PostMapping
    public ResponseEntity<Incident> createIncident(
            @RequestBody CreateIncidentRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails != null ? userDetails.getUsername() : "alex.morgan@company.com";
        return ResponseEntity.ok(incidentService.createIncident(request, email));
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<Incident> assignAgent(
            @PathVariable String id,
            @RequestBody Map<String, String> body,
            @AuthenticationPrincipal UserDetails userDetails) {
        String actorName = userDetails != null ? userDetails.getUsername() : "Admin";
        return ResponseEntity.ok(incidentService.assignAgent(id, body.get("agentId"), actorName));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Incident> updateStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> body,
            @AuthenticationPrincipal UserDetails userDetails) {
        Status newStatus = Status.valueOf(body.get("status").toUpperCase());
        String actorName = userDetails != null ? userDetails.getUsername() : "User";
        return ResponseEntity.ok(incidentService.updateStatus(id, newStatus, actorName));
    }

    @PostMapping("/{id}/resolve")
    public ResponseEntity<Incident> resolveIncident(
            @PathVariable String id,
            @RequestBody ResolutionRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        String actorName = userDetails != null ? userDetails.getUsername() : "Agent";
        return ResponseEntity.ok(incidentService.resolveIncident(id, request, actorName));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Incident> addComment(
            @PathVariable String id,
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        String authorName = userDetails != null ? userDetails.getUsername() : "User";
        return ResponseEntity.ok(incidentService.addComment(id, request, authorName, "User"));
    }
}
