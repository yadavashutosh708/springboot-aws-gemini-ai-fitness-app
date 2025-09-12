package com.fitness.aiservice.service;

import com.fitness.aiservice.dto.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityMessageListener implements IActivityMessageListener{

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    @Override
    public void processActivityMessage(Activity activity) {
        log.info("Received activity message: {}", activity);
        // Here you can add logic to process the activity message, e.g., analyze it using AI models
    }
}