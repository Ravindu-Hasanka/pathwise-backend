package com.example.pathwisebackend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "job_seekers")
@PrimaryKeyJoinColumn(name = "userId")
public class JobSeeker extends User {
    private String currentPosition;
    private String currentIndustry;
}
