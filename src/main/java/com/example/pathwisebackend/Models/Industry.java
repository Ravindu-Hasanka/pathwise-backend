package com.example.pathwisebackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "industries")
public class Industry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long industryId;

    @NotBlank(message = "Industry name is required")
    private String name;

//    @ManyToMany(mappedBy = "industries")
//    private Set<JobSeeker> jobSeekers = new HashSet<>();
//
//    @ManyToMany(mappedBy = "industries")
//    private Set<Coach> coaches = new HashSet<>();
}
