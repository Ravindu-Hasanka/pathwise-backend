package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRoleRepository extends JpaRepository<JobRole, Long> {
}
