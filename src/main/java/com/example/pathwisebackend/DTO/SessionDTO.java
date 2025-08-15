package com.example.pathwisebackend.DTO;

import com.example.pathwisebackend.Models.SessionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SessionDTO {
    private Long coachId;
    private Long studentId;
    private String sessionType;
    private LocalDateTime startTime;
    private Long duration;
    private String sessionMode;
    private SessionStatus status;
}

