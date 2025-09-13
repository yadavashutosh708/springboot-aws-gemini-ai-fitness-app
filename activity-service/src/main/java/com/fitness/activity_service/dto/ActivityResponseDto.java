package com.fitness.activity_service.dto;

import com.fitness.activity_service.model.ActivityType;
import com.mongodb.lang.NonNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ActivityResponseDto {

    @NonNull
    private String userId;

    private ActivityType type;
    private int duration;
    private int caloriesBurned;

    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
