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
@Table(name = "experiences")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long experienceId;

    @NotBlank(message = "Job role is required")
    private String jobRole;

    @NotBlank(message = "Company is required")
    private String company;

    @NotNull(message = "Start date is required")
    private Date startedAt;

    private Date endedAt;

    @ManyToMany(mappedBy = "industries")
    private Set<JobSeeker> jobSeekers = new HashSet<>();

    @ManyToMany(mappedBy = "industries")
    private Set<Coach> coaches = new HashSet<>();
}
