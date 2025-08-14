package com.example.pathwisebackend.dto;

import jakarta.persistence.ElementCollection;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CoachProfileDTO extends UserDTO {
    private String location;
    private List<String> expertiseArea;
    private String yearsOfExperience;
    private List<String> preferredIndustries;
    private String hourlyRate;
    private String description;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getExpertiseArea() {
        return expertiseArea;
    }

    public void setExpertiseArea(List<String> expertiseArea) {
        this.expertiseArea = expertiseArea;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<String> getPreferredIndustries() {
        return preferredIndustries;
    }

    public void setPreferredIndustries(List<String> preferredIndustries) {
        this.preferredIndustries = preferredIndustries;
    }

    public String getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(String hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
