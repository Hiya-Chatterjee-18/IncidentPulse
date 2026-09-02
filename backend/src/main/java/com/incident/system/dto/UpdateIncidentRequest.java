package com.incident.system.dto;

import com.incident.system.model.Priority;
import com.incident.system.model.Status;

public class UpdateIncidentRequest {
    private String title;
    private String description;
    private Priority priority;
    private Status status;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
