package com.incident.system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.List;

@Document(collection = "knowledge_base")
public class KnowledgeBaseArticle {

    @Id
    private String id;
    private String title;
    private String category;
    private String summary;
    private String rootCause;
    private String resolutionSteps;
    private List<String> tags;
    private Instant createdAt = Instant.now();

    public KnowledgeBaseArticle() {}

    public KnowledgeBaseArticle(String id, String title, String category, String summary, String rootCause, String resolutionSteps, List<String> tags) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.summary = summary;
        this.rootCause = rootCause;
        this.resolutionSteps = resolutionSteps;
        this.tags = tags;
        this.createdAt = Instant.now();
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getSummary() { return summary; }
    public String getRootCause() { return rootCause; }
    public String getResolutionSteps() { return resolutionSteps; }
    public List<String> getTags() { return tags; }
    public Instant getCreatedAt() { return createdAt; }
}
