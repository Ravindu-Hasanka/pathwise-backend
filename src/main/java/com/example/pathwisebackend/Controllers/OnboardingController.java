package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.DTO.User.CreateCoachDTO;
import com.example.pathwisebackend.DTO.User.CreateJobSeekerDTO;
import com.example.pathwisebackend.DTO.User.UpdateCoachDTO;
import com.example.pathwisebackend.DTO.User.UpdateJobSeekerDTO;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Services.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/onboarding")
@CrossOrigin(origins = "http://localhost:3000")
public class OnboardingController {
    @Autowired
    private  OnboardingService onboardingService;

    @PostMapping("/jobseeker")
    public ResponseEntity<User> createJobSeeker(@RequestBody CreateJobSeekerDTO dto) {
        User savedUser = onboardingService.createJobSeeker(dto);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/coach")
    public ResponseEntity<User> createCoach(@RequestBody CreateCoachDTO dto) {
        User savedUser = onboardingService.createCoach(dto);
        return ResponseEntity.ok(savedUser);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = onboardingService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
    @PutMapping("/jobseeker/{id}")
    public ResponseEntity<?> updateJobSeeker(
            @PathVariable Long id,
            @RequestBody UpdateJobSeekerDTO dto
    ) {
        try {
            User updatedUser = onboardingService.updateJobSeeker(id, dto);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Update Coach by ID
    @PutMapping("/coach/{id}")
    public ResponseEntity<?> updateCoach(
            @PathVariable Long id,
            @RequestBody UpdateCoachDTO dto
    ) {
        try {
            User updatedUser = onboardingService.updateCoach(id, dto);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}

