package com.fitness.activity_service.service;

import com.fitness.activity_service.dto.ActivityResponseDto;
import com.fitness.activity_service.model.Activity;
import com.fitness.activity_service.repository.IActivityRepository;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityService implements IActivityService {

    @Autowired
    private IActivityRepository activityRepository;

    @Autowired
    IUserValidationService userValidationService;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ActivityResponseDto trackActivity(ActivityResponseDto activityDto) {
        boolean isValidUser = userValidationService.validateUser(activityDto.getUserId());
        if(!isValidUser){
            throw new RuntimeException("Invalid user ID: " + activityDto.getUserId());
        }

        Activity activity = Activity.builder().
                userId(activityDto.getUserId()).
                type(activityDto.getType()).
                caloriesBurned(activityDto.getCaloriesBurned()).
                startTime(activityDto.getStartTime()).
                additionalMetrics(activityDto.getAdditionalMetrics()).build();

        Activity savedActivity = activityRepository.save(activity);
        log.info("Activity saved with id: {}", savedActivity.getId());
        try{
            rabbitTemplate.convertAndSend(exchange, routingKey, savedActivity);
        }catch (Exception e){
            log.error("Failed to send message to RabbitMQ: {}", e.getMessage());
        }
        return mapToDto(savedActivity);
    }

    @Override
    public List<ActivityResponseDto> getUserActivities(String userId) {
        List<Activity> userActivities = activityRepository.findByUserId(userId);
        return userActivities.stream().map(this::mapToDto).toList();
    }

    @Override
    public ActivityResponseDto getUserActivity(ObjectId activityId) {
        return activityRepository.findById(activityId).map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + activityId));
    }

    private ActivityResponseDto mapToDto(Activity activity) {
        ActivityResponseDto dto = new ActivityResponseDto(activity.getUserId());
        dto.setType(activity.getType());
        dto.setDuration(String.valueOf(activity.getDuration()));
        dto.setCaloriesBurned(activity.getCaloriesBurned());
        dto.setStartTime(activity.getStartTime());
        dto.setAdditionalMetrics(activity.getAdditionalMetrics());
        dto.setCreatedAt(activity.getCreatedAt());
        dto.setUpdatedAt(activity.getUpdatedAt());
        return dto;
    }
}
