package com.example.pathwisebackend.Models;

import com.example.pathwisebackend.Enum.SessionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "coaching_sessions")
@Data
public class CoachingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    private String sessionType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String sessionMode;
    @Column(length=500)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)  // Changed to coach_id
    private User coach;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)  // Changed to student_id
    private User student;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;
}