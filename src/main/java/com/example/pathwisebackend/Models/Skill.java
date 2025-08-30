package com.example.pathwisebackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;
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

    @Column(nullable = false)
    private int level = 0;

    @NotNull(message = "Issued date is required")
    @Column(nullable = false)
    private Date updatedAt = new Date(System.currentTimeMillis());

    @NotNull(message = "Created date is required")
    @Column(nullable = false)
    private Date createdAt;

    @ManyToMany(mappedBy = "skills")
    @JsonIgnore
    private Set<JobSeeker> jobSeekers = new HashSet<>();

    @ManyToMany(mappedBy = "skills")
    @JsonIgnore
    private Set<Coach> coaches = new HashSet<>();
}
