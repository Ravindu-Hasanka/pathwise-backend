package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.Models.CoachProfile;
import com.example.pathwisebackend.Models.JobSeekerProfile;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.CoachProfileRepository;
import com.example.pathwisebackend.Repositories.JobSeekerProfileRepository;
import com.example.pathwisebackend.Repositories.UserRepository;
import com.example.pathwisebackend.dto.CoachProfileDTO;
import com.example.pathwisebackend.dto.JobSeekerProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class OnboardingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private   JobSeekerProfileRepository jobSeekerProfileRepository;
    @Autowired
    private   CoachProfileRepository coachProfileRepository;

    public User createJobSeeker(JobSeekerProfileDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole("JOB_SEEKER");

        JobSeekerProfile profile = new JobSeekerProfile();
        profile.setLocation(dto.getLocation());
        profile.setCurrentRole(dto.getCurrentRole());
        profile.setExperience(dto.getExperience());
        profile.setEducation(dto.getEducation());
        profile.setSkills(dto.getSkills());
        profile.setInterests(dto.getInterests());
        profile.setExpertiseArea(dto.getExpertiseArea());
        profile.setCareerGoals(dto.getCareerGoals());
        profile.setTargetRole(dto.getTargetRole());
        profile.setTargetIndustry(dto.getTargetIndustry());
        profile.setSalaryExpectation(dto.getSalaryExpectation());

        profile.setUser(user);
        user.setJobSeekerProfile(profile);

        return userRepository.save(user);
    }

    public User createCoach(CoachProfileDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole("COACH");

        CoachProfile profile = new CoachProfile();
        profile.setLocation(dto.getLocation());
        profile.setExpertiseArea(dto.getExpertiseArea());
        profile.setYearsOfExperience(dto.getYearsOfExperience());
        profile.setPreferredIndustries(dto.getPreferredIndustries());
        profile.setHourlyRate(dto.getHourlyRate());
        profile.setDescription(dto.getDescription());

        profile.setUser(user);
        user.setCoachProfile(profile);

        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User updateJobSeeker(Long userId, JobSeekerProfileDTO dto) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        JobSeekerProfile profile = user.getJobSeekerProfile();
        profile.setLocation(dto.getLocation());
        profile.setCurrentRole(dto.getCurrentRole());
        profile.setExperience(dto.getExperience());
        profile.setEducation(dto.getEducation());
        profile.setSkills(dto.getSkills());
        profile.setInterests(dto.getInterests());
        profile.setExpertiseArea(dto.getExpertiseArea());
        profile.setCareerGoals(dto.getCareerGoals());
        profile.setTargetRole(dto.getTargetRole());
        profile.setTargetIndustry(dto.getTargetIndustry());
        profile.setSalaryExpectation(dto.getSalaryExpectation());

        return userRepository.save(user);
    }

    // Update Coach by ID
    public User updateCoach(Long userId, CoachProfileDTO dto) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        CoachProfile profile = user.getCoachProfile();
        profile.setLocation(dto.getLocation());
        profile.setExpertiseArea(dto.getExpertiseArea());
        profile.setYearsOfExperience(dto.getYearsOfExperience());
        profile.setPreferredIndustries(dto.getPreferredIndustries());
        profile.setHourlyRate(dto.getHourlyRate());
        profile.setDescription(dto.getDescription());

        return userRepository.save(user);
    }
}
