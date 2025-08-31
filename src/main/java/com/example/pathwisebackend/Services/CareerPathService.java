package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.IndustryWithRolesDto;
import com.example.pathwisebackend.Enum.UserRoles;
import com.example.pathwisebackend.Models.Industry;
import com.example.pathwisebackend.Models.JobRole;
import com.example.pathwisebackend.Models.JobSeeker;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.IndustryRepository;
import com.example.pathwisebackend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CareerPathService {
    private final GeminiService geminiService;
    private final UserRepository userRepository;
    private final IndustryRepository industryRepository;

    public List<Map<String, Object>> getCareerPaths(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return new ArrayList<>();
        }

        User user = optionalUser.get();

        String currentRole;
        if(user.getRole() == UserRoles.JOB_SEEKER) {
            JobSeeker jobSeeker = (JobSeeker) user;
            currentRole = jobSeeker.getCurrentPosition().toString() + " in " + jobSeeker.getCurrentIndustry().toString();
        } else {
            currentRole = " ";
        }

        List<Industry> industries = industryRepository.findByOwner_Id(userId);
        System.out.println("industries: " + industries);

        List<IndustryWithRolesDto> industryWithRolesDtoList = industries.stream()
                .map(i -> new IndustryWithRolesDto(
                        i.getName(),
                        i.getJobRoles().stream()
                                .map(JobRole::getJobRoleName)
                                .collect(Collectors.toList())
                ))
                .toList();

        System.out.println("industryWithRolesDtoList: " + industryWithRolesDtoList);

        // Build job list
        List<String> jobs = new ArrayList<>();
        for (IndustryWithRolesDto industryWithRolesDto : industryWithRolesDtoList) {
            for (String jobRole : industryWithRolesDto.jobRoles()) {
                jobs.add(jobRole + " in " + industryWithRolesDto.industryName());
            }
        }

        System.out.println("jobs: " + jobs);

        return geminiService.getCareerPaths(jobs, currentRole);
    }

}
