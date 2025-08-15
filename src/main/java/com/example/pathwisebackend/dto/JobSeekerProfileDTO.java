package com.example.pathwisebackend.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class JobSeekerProfileDTO extends UserDTO {
    private String location;
    private String currentRole;
    private String experience;
    private String education;
    private List<String> skills;
    private List<String> interests;
    private List<String> expertiseArea;
    private String careerGoals;
    private String targetRole;
    private String targetIndustry;
    private String salaryExpectation;
}
