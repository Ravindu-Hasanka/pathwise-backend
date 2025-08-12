package com.example.pathwisebackend.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "job_seeker_profile")
public class JobSeekerProfile {
    @Id
    private Long id;

    private String location;
    private String currentRole;
    private String experience;
    private String education;

    @ElementCollection
    private List<String> skills;

    @ElementCollection
    private List<String> interests;

    private String careerGoals;
    private String targetRole;
    private String targetIndustry;
    private String salaryExpectation;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}

