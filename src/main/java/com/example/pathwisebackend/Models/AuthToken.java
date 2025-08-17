package com.example.pathwisebackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name="auth_token")
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authTokenId;

    @NotBlank(message = "Token can't be empty")
    @Column(nullable = false, unique = true)
    private String token;

    @NotNull(message = "Issued date is required")
    @Column(nullable = false)
    private Date issuedAt;

    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in the future")
    @Column(nullable = false)
    private Date expiresAt;

    @NotNull(message = "isValid status is required")
    @Column(nullable = false)
    private Boolean isValid = true;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
