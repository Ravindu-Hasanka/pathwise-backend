package com.example.pathwisebackend.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private JobSeekerProfile jobSeekerProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CoachProfile coachProfile;

    @OneToMany(mappedBy = "createdBy")
    @JsonBackReference
    private List<Post> posts;

}
