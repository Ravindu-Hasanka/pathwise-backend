package com.example.pathwisebackend.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "coaches")
@PrimaryKeyJoinColumn(name = "userId")
public class Coach extends User {

    private String description;

    @ManyToMany
    @JoinTable(
            name = "coach_experiences",
            joinColumns = @JoinColumn(name = "coach_id"),
            inverseJoinColumns = @JoinColumn(name = "experience_id")
    )
    private Set<Experience> experiences = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "coach_skills",
            joinColumns = @JoinColumn(name = "coach_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "coach_educations",
            joinColumns = @JoinColumn(name = "coach_id"),
            inverseJoinColumns = @JoinColumn(name = "education_id")
    )
    private Set<Education> educations = new HashSet<>();
}
