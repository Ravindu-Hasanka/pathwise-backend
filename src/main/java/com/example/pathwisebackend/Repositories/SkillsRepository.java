package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepository extends JpaRepository <Skill, Long> {
    List<Skill> findByJobSeekers_Id(Long jobSeekerId);
    List<Skill> findByCoaches_Id(Long coachId);
}
