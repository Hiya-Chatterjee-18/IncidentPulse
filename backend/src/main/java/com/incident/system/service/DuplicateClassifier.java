package com.incident.system.service;

import com.incident.system.model.Incident;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DuplicateClassifier {

    private static final double SIMILARITY_THRESHOLD = 0.60;

    public Optional<Incident> findDuplicate(String newTitle, String newDescription, List<Incident> openIncidents) {
        Set<String> newTokens = tokenize(newTitle + " " + newDescription);

        for (Incident openIncident : openIncidents) {
            Set<String> existingTokens = tokenize(openIncident.getTitle() + " " + openIncident.getDescription());
            double similarity = calculateJaccardSimilarity(newTokens, existingTokens);

            if (similarity >= SIMILARITY_THRESHOLD) {
                return Optional.of(openIncident);
            }
        }
        return Optional.empty();
    }

    private double calculateJaccardSimilarity(Set<String> setA, Set<String> setB) {
        if (setA.isEmpty() || setB.isEmpty()) return 0.0;

        Set<String> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);

        Set<String> union = new HashSet<>(setA);
        union.addAll(setB);

        return (double) intersection.size() / union.size();
    }

    private Set<String> tokenize(String text) {
        return Arrays.stream(text.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").split("\\s+"))
                .filter(word -> word.length() > 3)
                .collect(Collectors.toSet());
    }
}
