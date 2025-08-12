package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.JobSeekerProfile;
import com.example.pathwisebackend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile,Long> {
}
