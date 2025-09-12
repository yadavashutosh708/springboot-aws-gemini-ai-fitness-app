package com.fitness.user_service.service;

import com.fitness.user_service.dto.RegisterRequest;
import com.fitness.user_service.dto.UserResponse;
import com.fitness.user_service.model.User;
import com.fitness.user_service.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUserService{

    @Autowired
    IUserRepository userRepository;
    @Override
    public UserResponse getUserProfile(String userId) {
        log.info("Fetching user profile for userId: {}", userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponse(user);
    }

    @Override
    public UserResponse registerUser(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser);
    }

    @Override
    public Boolean existsByUserId(String userId) {
        log.info("Calling user Validation API for userId: {}", userId);
        return userRepository.existsById(userId);
    }
}
