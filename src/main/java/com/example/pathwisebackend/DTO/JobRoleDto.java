package com.example.pathwisebackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class JobRoleDto {
    private Long jobRoleId;
    private String jobRoleName;
    private Double minSalary;
    private Double maxSalary;
    private Double hourlyTeachingRate;

    private List<SkillDto> skills;
    private List<CourseDto> courses;
}
