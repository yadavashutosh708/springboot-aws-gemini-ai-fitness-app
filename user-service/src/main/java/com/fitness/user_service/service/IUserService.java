package com.fitness.user_service.service;

import com.fitness.user_service.dto.RegisterRequest;
import com.fitness.user_service.dto.UserResponse;

public interface IUserService {

    public UserResponse getUserProfile(String userId);

    public UserResponse registerUser(RegisterRequest request);

    Boolean existsByUserId(String userId);
}
