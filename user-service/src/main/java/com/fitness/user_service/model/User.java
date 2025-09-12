package com.fitness.user_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity //Model class for User
@Table(name = "users") // Specify the table name in the database
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.UUID)// Auto-generate UUIDs for IDs
    private String id;

    @Column(unique = true)// Ensure email is unique
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
