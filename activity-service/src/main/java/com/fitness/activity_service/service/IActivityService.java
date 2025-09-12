package com.fitness.activity_service.service;

import com.fitness.activity_service.dto.ActivityResponseDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface IActivityService {
    ActivityResponseDto trackActivity(ActivityResponseDto activityDto);

    List<ActivityResponseDto> getUserActivities(String userId);

    ActivityResponseDto getUserActivity(ObjectId activityId);
}
