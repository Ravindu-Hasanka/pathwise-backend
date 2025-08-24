package com.example.pathwisebackend.DTO.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    Long userId;
    String accessToken;
    String refreshToken;
    String message;
}
