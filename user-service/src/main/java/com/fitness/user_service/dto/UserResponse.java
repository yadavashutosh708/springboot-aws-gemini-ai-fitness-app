package com.fitness.user_service.dto;

import com.fitness.user_service.model.User;
import lombok.Data;

@Data
public class UserResponse {

    private String id;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String createdAt;
    private String updatedAt;

    public UserResponse(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.createdAt = user.getCreatedAt().toString();
        this.updatedAt = user.getUpdatedAt().toString();
    }
}
