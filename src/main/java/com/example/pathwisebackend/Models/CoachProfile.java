package com.example.pathwisebackend.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "coach_profile")
public class CoachProfile {
    @Id
    private Long id;

    private String location;
    private String expertiseArea;
    private String yearsOfExperience;
    private String preferredIndustries;
    private Double hourlyRate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}

