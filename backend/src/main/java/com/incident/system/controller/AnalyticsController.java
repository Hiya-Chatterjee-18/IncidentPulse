package com.incident.system.controller;

import com.incident.system.dto.AnalyticsMetricsResponse;
import com.incident.system.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/metrics")
    public ResponseEntity<AnalyticsMetricsResponse> getMetrics() {
        return ResponseEntity.ok(analyticsService.getAnalyticsMetrics());
    }
}
