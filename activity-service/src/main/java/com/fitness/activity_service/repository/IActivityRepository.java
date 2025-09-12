package com.fitness.activity_service.repository;

import com.fitness.activity_service.model.Activity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IActivityRepository extends MongoRepository<Activity, ObjectId> {
    List<Activity> findByUserId(String userId);
}
