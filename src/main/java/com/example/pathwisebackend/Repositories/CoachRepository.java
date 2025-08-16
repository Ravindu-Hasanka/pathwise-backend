package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
}
