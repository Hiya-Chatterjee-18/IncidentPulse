package com.incident.system.service;

import com.incident.system.model.Priority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IntelligentPriorityEngine {

    private static final List<String> CRITICAL_VOCABULARY = List.of("outage", "down", "crash", "data loss", "security breach", "production", "db failure");
    private static final List<String> HIGH_VOCABULARY = List.of("slow", "latency", "payment", "auth failure", "error 500", "memory leak");

    public Priority classifyPriority(String title, String description, String userUrgency, String userImpact) {
        String fullText = (title + " " + description).toLowerCase();

        long criticalHits = CRITICAL_VOCABULARY.stream().filter(fullText::contains).count();
        long highHits = HIGH_VOCABULARY.stream().filter(fullText::contains).count();

        if (criticalHits >= 1 || ("Critical".equalsIgnoreCase(userUrgency) && "Organization".equalsIgnoreCase(userImpact))) {
            return Priority.CRITICAL;
        } else if (highHits >= 1 || "High".equalsIgnoreCase(userUrgency)) {
            return Priority.HIGH;
        } else if ("Medium".equalsIgnoreCase(userUrgency)) {
            return Priority.MEDIUM;
        }
        return Priority.LOW;
    }
}
