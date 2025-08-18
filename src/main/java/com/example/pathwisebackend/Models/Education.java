package com.example.pathwisebackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long educationId;

    @NotBlank(message = "Degree/Name is required")
    private String name;

    @NotBlank(message = "Institution is required")
    private String institution;

    @NotNull(message = "Start date is required")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate = new Date();

    @ManyToMany(mappedBy = "educations")
    private Set<JobSeeker> jobSeekers = new HashSet<>();

    @ManyToMany(mappedBy = "educations")
    private Set<Coach> coaches = new HashSet<>();
}
