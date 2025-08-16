package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Integer> {
}
