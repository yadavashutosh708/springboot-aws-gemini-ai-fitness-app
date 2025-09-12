package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.IRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService implements IRecommendationService{

    @Autowired
    IRecommendationRepository iRecommendationRepository;

    @Override
    public List<Recommendation> getUserRecommendations(String userId) {
        return iRecommendationRepository.findByUserId(userId);
    }

    @Override
    public Recommendation getActivityRecommendation(String activityId) {
        return iRecommendationRepository.findByActivityId(activityId).orElseThrow(() -> new RuntimeException("No recommendation found for activityId: " + activityId));
    }
}