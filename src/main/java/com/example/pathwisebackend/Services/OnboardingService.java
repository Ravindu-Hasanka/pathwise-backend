package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.User.CreateCoachDTO;
import com.example.pathwisebackend.DTO.User.CreateJobSeekerDTO;
import com.example.pathwisebackend.DTO.User.UpdateCoachDTO;
import com.example.pathwisebackend.DTO.User.UpdateJobSeekerDTO;
import com.example.pathwisebackend.Enum.UserRoles;
import com.example.pathwisebackend.Models.*;
import com.example.pathwisebackend.Repositories.*;
import com.example.pathwisebackend.DTO.CoachProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class OnboardingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    @Autowired
    private CoachRepository coachRepository;

    public JobSeeker createJobSeeker(CreateJobSeekerDTO dto) {
        JobSeeker jobSeeker = new JobSeeker();

        jobSeeker.setName(dto.getName());
        jobSeeker.setEmail(dto.getEmail());
        jobSeeker.setPassword(dto.getPassword());
        jobSeeker.setPhone(dto.getPhone());
        jobSeeker.setAddress(dto.getAddress());
        jobSeeker.setRole(UserRoles.JOB_SEEKER);
        jobSeeker.setIsActive(true);

        jobSeeker.setCurrentPosition(dto.getCurrentPosition());
        jobSeeker.setCurrentIndustry(dto.getCurrentIndustry());

        return jobSeekerRepository.save(jobSeeker);
    }


    public User createCoach(CreateCoachDTO dto) {
        Coach coach = new Coach();
        coach.setName(dto.getName());
        coach.setEmail(dto.getEmail());
        coach.setPassword(dto.getPassword());
        coach.setPhone(dto.getPhone());
        coach.setAddress(dto.getAddress());
        coach.setRole(UserRoles.COACH);
        coach.setIsActive(true);

        coach.setDescription(dto.getDescription());

        return coachRepository.save(coach);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User updateJobSeeker(Long userId, UpdateJobSeekerDTO dto) throws Exception {
        JobSeeker jobSeeker = jobSeekerRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));

        if (dto.getName() != null && !dto.getName().isBlank()) {
            jobSeeker.setName(dto.getName());
        }
        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            jobSeeker.setEmail(dto.getEmail());
        }
        if (dto.getPhone() != null && !dto.getPhone().isBlank()) {
            jobSeeker.setPhone(dto.getPhone());
        }
        if (dto.getAddress() != null && !dto.getAddress().isBlank()) {
            jobSeeker.setAddress(dto.getAddress());
        }
        if(dto.getCurrentIndustry() != null && !dto.getCurrentIndustry().isBlank()){
            jobSeeker.setCurrentIndustry(dto.getCurrentIndustry());
        }
        if(dto.getCurrentPosition() != null && !dto.getCurrentPosition().isBlank()){
            jobSeeker.setCurrentPosition(dto.getCurrentPosition());
        }

        return jobSeekerRepository.save(jobSeeker);

    }

    // Update Coach by ID
    public User updateCoach(Long userId, UpdateCoachDTO dto) throws Exception {
        Coach coach = coachRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));

        if(dto.getName() != null && !dto.getName().isBlank()){
            coach.setName(dto.getName());
        }
        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            Optional<User> existingUser = userRepository.findByEmail(dto.getEmail());

            if (existingUser.isPresent() && !existingUser.get().getId().equals(coach.getId())) {
                throw new Exception("User with this email already exists");
            }
            coach.setEmail(dto.getEmail());
        }
        if(dto.getPhone() != null && !dto.getPhone().isBlank()){
            coach.setPhone(dto.getPhone());
        }
        if(dto.getAddress() != null && !dto.getAddress().isBlank()){
            coach.setAddress(dto.getAddress());
        }
        if(dto.getDescription() != null && !dto.getDescription().isBlank()){
            coach.setDescription(dto.getDescription());
        }

        return coachRepository.save(coach);
    }
}
