package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Recommendation;

import java.util.List;
import java.util.Optional;

public interface IRecommendationService{
    public List<Recommendation> getUserRecommendations(String userId);

    public Recommendation getActivityRecommendation(String activityId) ;
}
