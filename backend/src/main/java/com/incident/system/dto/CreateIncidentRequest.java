package com.incident.system.dto;

import com.incident.system.model.Priority;

public class CreateIncidentRequest {
    private String title;
    private String category;
    private String description;
    private Priority priority;
    private String impact;
    private String urgency;
    private String affectedSystem;
    private String location;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public String getImpact() { return impact; }
    public void setImpact(String impact) { this.impact = impact; }
    public String getUrgency() { return urgency; }
    public void setUrgency(String urgency) { this.urgency = urgency; }
    public String getAffectedSystem() { return affectedSystem; }
    public void setAffectedSystem(String affectedSystem) { this.affectedSystem = affectedSystem; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
