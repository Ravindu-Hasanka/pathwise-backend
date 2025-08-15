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
    private String location; // no need
    @ElementCollection
    private List<String> expertiseArea; // ui ux, frontend developer
    private String yearsOfExperience; // years of experience
    @ElementCollection
    private List<String> preferredIndustries; // his industry
    private String hourlyRate;
    private String description; // like bio
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}

