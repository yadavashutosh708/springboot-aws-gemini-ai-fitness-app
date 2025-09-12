package com.fitness.activity_service.service;

import com.fitness.activity_service.dto.UserValidationResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserValidationService implements IUserValidationService{

    private final WebClient userServiceWebClient;

    @Override
    public boolean validateUser(String userId) {
        log.info("Calling user Validation API for userId: {}", userId);
        try {
            UserValidationResponseDto userValidationResponse = userServiceWebClient.get()
                    .uri("/api/users/validate/{userId}", userId)
                    .retrieve()
                    .bodyToMono(UserValidationResponseDto.class)
                    .block();

            log.info("Calling user Validation API for userId: {}",userValidationResponse != null && userValidationResponse.isValid());
            return userValidationResponse != null && userValidationResponse.isValid();
        } catch (WebClientResponseException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new RuntimeException("User not found with id: " + userId);
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new RuntimeException("Invalid user ID format: " + userId);
            } else {
                throw new RuntimeException("Error validating user with id: " + userId + ". Status code: " + e.getStatusCode());
            }
        }
    }
}
