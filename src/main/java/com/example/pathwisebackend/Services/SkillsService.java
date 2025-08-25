package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.IndustryDto;
import com.example.pathwisebackend.DTO.JobRoleDto;
import com.example.pathwisebackend.Models.Industry;
import com.example.pathwisebackend.Models.JobSeeker;
import com.example.pathwisebackend.Repositories.IndustryRepository;
import com.example.pathwisebackend.Repositories.JobSeekerRepository;
import com.example.pathwisebackend.Repositories.SkillsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillsService {
    private final SkillsRepository skillsRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final IndustryRepository industryRepository;
    private final GeminiService geminiService;

    public Map<String, Object> getRecommendedResources(Long userId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(userId).get();

        List<Industry> industries = industryRepository.findByOwnerId(userId);

        List<IndustryDto> industryDtos = industries.stream().map(industry ->
                new IndustryDto(
                        industry.getIndustryId(),
                        industry.getName(),
                        industry.getJobRoles().stream()
                                .map(jobRole -> {
                                    JobRoleDto dto = new JobRoleDto();
                                    dto.setJobRoleId(jobRole.getJobRoleId());
                                    dto.setJobRoleName(jobRole.getJobRoleName());
                                    dto.setMinSalary(jobRole.getMinSalary());
                                    dto.setMaxSalary(jobRole.getMaxSalary());
                                    dto.setHourlyTeachingRate(jobRole.getHourlyTeachingRate());
                                    return dto;
                                })
                                .collect(Collectors.toList())
                )
        ).collect(Collectors.toList());

        // ✅ Manually add IT -> Software Engineer
        JobRoleDto softwareEngineer = new JobRoleDto();
        softwareEngineer.setJobRoleId(999L);
        softwareEngineer.setJobRoleName("Software Engineer");
        softwareEngineer.setMinSalary(80000.0);
        softwareEngineer.setMaxSalary(200000.0);
        softwareEngineer.setHourlyTeachingRate(50.0);

        IndustryDto newIndustry = new IndustryDto(
                100L,
                "Information Technology",
                List.of(softwareEngineer)
        );

        industryDtos.add(newIndustry);

        return geminiService.runInterviewPrepFlow(industryDtos);
    }

}
