package com.example.pathwisebackend.DTO;

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

}
