package com.incident.system.model;

import java.time.Instant;

public class Comment {
    private String id;
    private String authorName;
    private String authorRole;
    private String text;
    private boolean isInternal;
    private Instant createdAt = Instant.now();

    public Comment() {}

    public Comment(String id, String authorName, String authorRole, String text, boolean isInternal) {
        this.id = id;
        this.authorName = authorName;
        this.authorRole = authorRole;
        this.text = text;
        this.isInternal = isInternal;
        this.createdAt = Instant.now();
    }

    public String getId() { return id; }
    public String getAuthorName() { return authorName; }
    public String getAuthorRole() { return authorRole; }
    public String getText() { return text; }
    public boolean isInternal() { return isInternal; }
    public Instant getCreatedAt() { return createdAt; }
}
