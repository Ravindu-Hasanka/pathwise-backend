package com.example.pathwisebackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String experience; // currently  0-1 years
    private String education; // masters
    @ElementCollection
    private List<String> skills; // nextjs, java etc
    @ElementCollection
    private List<String> interests; // technology, finance etc
    @ElementCollection
    private List<String> expertiseArea; // subcategories of the interests
    private String careerGoals; // remove 0r check again
    private String targetRole; // expected role
    private String targetIndustry; // expected indus    try
    private String salaryExpectation; // expected salary (better if give the range)
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}

