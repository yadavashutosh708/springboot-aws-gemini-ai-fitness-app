package com.fitness.activity_service.controller;

import com.fitness.activity_service.dto.ActivityResponseDto;
import com.fitness.activity_service.service.IActivityService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities/")
public class ActivityController {

    @Autowired
    IActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponseDto> trackActivity(@RequestBody ActivityResponseDto activityDto) {
        // Implementation for tracking activity
        return ResponseEntity.ok(activityService.trackActivity(activityDto));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponseDto>> getUserActivities(@RequestHeader("X-User-Id") String userId) {
        // Implementation for retrieving user activities
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }

    @GetMapping({"/{activityId}"})
    public ResponseEntity<ActivityResponseDto> getUserActivity(@PathVariable ObjectId activityId) {
        // Implementation for retrieving user activities
        return ResponseEntity.ok(activityService.getUserActivity(activityId));
    }
}
