package com.example.pathwisebackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Data
@Table(name = "job_role")
public class JobRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobRoleId;

    @NotBlank(message = "Job role name is required")
    private String jobRoleName;

    @PositiveOrZero(message = "Minimum salary must be zero or positive")
    private Double minSalary;

    @Positive(message = "Maximum salary must be positive")
    private Double maxSalary;

    @PositiveOrZero(message = "Hourly teaching rate must be zero or positive")
    private Double hourlyTeachingRate;
}
