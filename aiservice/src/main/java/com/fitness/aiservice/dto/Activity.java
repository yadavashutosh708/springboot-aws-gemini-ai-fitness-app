package com.fitness.aiservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Activity {
    private Object id;
    private String userId;
    private String type; // e.g., "running", "cycling"
    private Integer duration; // in minutes
    private Integer caloriesBurned; // in kilometers
    private LocalDateTime startTime; // ISO 8601 format
    private Map<String, Object> additionalMetrics;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
