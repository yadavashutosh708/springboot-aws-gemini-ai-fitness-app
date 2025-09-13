package com.fitness.aiservice.service;

import com.fitness.aiservice.dto.Activity;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.IRecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityMessageListener implements IActivityMessageListener{

    private final ActivityAiService aiService;

    private final IRecommendationRepository recommendationRepository;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    @Override
    public void processActivityMessage(Activity activity) {
        log.info("Received activity message: {}", activity.getId());
        Recommendation recommendation = aiService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
        log.info("Saved recommendation for activity: {}", activity.getId());
    }
}