package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.RelatedSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatedSkillsRepository extends JpaRepository<RelatedSkills, Long> {
}
