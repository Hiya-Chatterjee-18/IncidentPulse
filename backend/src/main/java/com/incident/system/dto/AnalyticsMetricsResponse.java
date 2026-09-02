package com.incident.system.dto;

import java.util.Map;

public class AnalyticsMetricsResponse {
    private long totalIncidents;
    private long openIncidents;
    private long resolvedIncidents;
    private double slaCompliancePercentage;
    private double avgMttrHours;
    private Map<String, Long> categoryDistribution;
    private Map<String, Long> priorityDistribution;

    public AnalyticsMetricsResponse(long totalIncidents, long openIncidents, long resolvedIncidents, double slaCompliancePercentage, double avgMttrHours, Map<String, Long> categoryDistribution, Map<String, Long> priorityDistribution) {
        this.totalIncidents = totalIncidents;
        this.openIncidents = openIncidents;
        this.resolvedIncidents = resolvedIncidents;
        this.slaCompliancePercentage = slaCompliancePercentage;
        this.avgMttrHours = avgMttrHours;
        this.categoryDistribution = categoryDistribution;
        this.priorityDistribution = priorityDistribution;
    }

    public long getTotalIncidents() { return totalIncidents; }
    public long getOpenIncidents() { return openIncidents; }
    public long getResolvedIncidents() { return resolvedIncidents; }
    public double getSlaCompliancePercentage() { return slaCompliancePercentage; }
    public double getAvgMttrHours() { return avgMttrHours; }
    public Map<String, Long> getCategoryDistribution() { return categoryDistribution; }
    public Map<String, Long> getPriorityDistribution() { return priorityDistribution; }
}
