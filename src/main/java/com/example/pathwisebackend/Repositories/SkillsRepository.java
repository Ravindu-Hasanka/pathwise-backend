package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends JpaRepository <Skill, Long> {
}
