package com.example.pathwisebackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "coach_profile")
public class CoachProfile {
    @Id
    private Long id;
    private String location;
    @ElementCollection
    private List<String> expertiseArea;
    private String yearsOfExperience;
    @ElementCollection
    private List<String> preferredIndustries;
    private String hourlyRate;
    private String description;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}

