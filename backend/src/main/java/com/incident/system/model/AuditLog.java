package com.incident.system.model;

import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document(collection = "audit_logs")
public class AuditLog {
    private String actor;
    private String action;
    private String details;
    private Instant timestamp = Instant.now();

    public AuditLog() {}

    public AuditLog(String actor, String action, String details) {
        this.actor = actor;
        this.action = action;
        this.details = details;
        this.timestamp = Instant.now();
    }

    public String getActor() { return actor; }
    public String getAction() { return action; }
    public String getDetails() { return details; }
    public Instant getTimestamp() { return timestamp; }
}
