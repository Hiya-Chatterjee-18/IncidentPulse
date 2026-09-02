package com.incident.system.model;

import java.time.Instant;

public class Resolution {
    private String rootCause;
    private String workaround;
    private String actionTaken;
    private String resolvedBy;
    private Instant resolvedAt = Instant.now();

    public Resolution() {}

    public Resolution(String rootCause, String workaround, String actionTaken, String resolvedBy) {
        this.rootCause = rootCause;
        this.workaround = workaround;
        this.actionTaken = actionTaken;
        this.resolvedBy = resolvedBy;
        this.resolvedAt = Instant.now();
    }

    public String getRootCause() { return rootCause; }
    public String getWorkaround() { return workaround; }
    public String getActionTaken() { return actionTaken; }
    public String getResolvedBy() { return resolvedBy; }
    public Instant getResolvedAt() { return resolvedAt; }
}
