package com.example.pathwisebackend.Models;

import com.example.pathwisebackend.Enum.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is required")
    private UserRoles role = UserRoles.JOB_SEEKER;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;

    private Boolean isActive = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuthToken> authTokens;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "initiator")
    private List<Connection> sentConnections;

    @OneToMany(mappedBy = "receiver")
    private List<Connection> receivedConnections;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoachingSession> coachingSessionsAsCoach;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoachingSession> coachingSessionsAsStudent;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Industry> industries;
}
