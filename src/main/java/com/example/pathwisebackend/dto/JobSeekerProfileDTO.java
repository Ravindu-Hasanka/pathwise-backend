package com.example.pathwisebackend.dto;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(String currentRole) {
        this.currentRole = currentRole;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public List<String> getExpertiseArea() {
        return expertiseArea;
    }

    public void setExpertiseArea(List<String> expertiseArea) {
        this.expertiseArea = expertiseArea;
    }

    public String getCareerGoals() {
        return careerGoals;
    }

    public void setCareerGoals(String careerGoals) {
        this.careerGoals = careerGoals;
    }

    public String getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(String targetRole) {
        this.targetRole = targetRole;
    }

    public String getTargetIndustry() {
        return targetIndustry;
    }

    public void setTargetIndustry(String targetIndustry) {
        this.targetIndustry = targetIndustry;
    }

    public String getSalaryExpectation() {
        return salaryExpectation;
    }

    public void setSalaryExpectation(String salaryExpectation) {
        this.salaryExpectation = salaryExpectation;
    }

    private String targetRole;
    private String targetIndustry;
    private String salaryExpectation;
}
