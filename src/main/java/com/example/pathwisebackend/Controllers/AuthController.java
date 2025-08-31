package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.DTO.Auth.AuthenticationResponse;
import com.example.pathwisebackend.DTO.Auth.LoginRequestDto;
import com.example.pathwisebackend.DTO.Auth.RegisterRequestDto;
import com.example.pathwisebackend.Models.JobSeeker;
import com.example.pathwisebackend.Services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin 
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequestDto request) {
        AuthenticationResponse jobSeeker = authService.register(request);
        return ResponseEntity.ok(jobSeeker);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequestDto request) {
        AuthenticationResponse response = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(response);
    }
}
