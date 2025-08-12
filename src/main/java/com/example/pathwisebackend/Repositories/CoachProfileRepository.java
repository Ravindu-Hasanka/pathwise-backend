package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.CoachProfile;
import com.example.pathwisebackend.Models.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachProfileRepository extends JpaRepository<CoachProfile,Long> {
}
