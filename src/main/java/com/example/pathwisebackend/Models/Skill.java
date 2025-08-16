package com.example.pathwisebackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    @NotBlank(message = "Skill name is required")
    private String name;

    @ManyToMany(mappedBy = "skills")
    private Set<JobSeeker> jobSeekers = new HashSet<>();

    @ManyToMany(mappedBy = "skills")
    private Set<Coach> coaches = new HashSet<>();
}
