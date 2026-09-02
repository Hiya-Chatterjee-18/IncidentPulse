package com.incident.system.model;

public enum Priority {
    CRITICAL(1, 4),
    HIGH(2, 8),
    MEDIUM(4, 24),
    LOW(8, 48);

    private final long responseSlaHours;
    private final long resolveSlaHours;

    Priority(long responseSlaHours, long resolveSlaHours) {
        this.responseSlaHours = responseSlaHours;
        this.resolveSlaHours = resolveSlaHours;
    }

    public long getResponseSlaHours() { return responseSlaHours; }
    public long getResolveSlaHours() { return resolveSlaHours; }
}
