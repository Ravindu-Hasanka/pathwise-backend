package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.IndustryDto;
import com.example.pathwisebackend.DTO.JobRoleDto;
import com.example.pathwisebackend.DTO.UpdateSkillLevelDto;
import com.example.pathwisebackend.Models.Industry;
import com.example.pathwisebackend.Models.JobRole;
import com.example.pathwisebackend.Models.JobSeeker;
import com.example.pathwisebackend.Models.Skill;
import com.example.pathwisebackend.Repositories.IndustryRepository;
import com.example.pathwisebackend.Repositories.JobSeekerRepository;
import com.example.pathwisebackend.Repositories.SkillsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillsService {
    private final SkillsRepository skillsRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final IndustryRepository industryRepository;
    private final GeminiService geminiService;

    public List<Map<String, Object>> getRecommendedResources(Long userId) {
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

    public List<Map<String, Object>> getRecommendedJobs(Long userId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(userId).get();

        List<Industry> industries = industryRepository.findByOwnerId(userId);
        List<String> jobNames = industries.stream()
                .flatMap(industry -> industry.getJobRoles().stream()
                        .map(JobRole::getJobRoleName))
                .collect(Collectors.toList());

        jobNames.add("Software Engineer");

        return geminiService.getRelatedJobs(jobNames);
    }

    public List<Skill> getUserSkills(Long userId) {
        List<Skill> skills = new ArrayList<>();
        List<Skill> coachSkills = skillsRepository.findByCoaches_Id(userId);
        skills.addAll(coachSkills);
        List<Skill> jobSeekerSkills = skillsRepository.findByJobSeekers_Id(userId);
        skills.addAll(jobSeekerSkills);
        return skills;
    }

    public void updateSkillScore(UpdateSkillLevelDto skillLevelDto) {
        Skill skill = skillsRepository.findById(skillLevelDto.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        skill.setLevel(skillLevelDto.getLevel());
        skill.setUpdatedAt(new Date(System.currentTimeMillis()));
        skillsRepository.save(skill);
    }

}
