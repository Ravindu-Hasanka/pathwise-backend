package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.SessionDTO;
import com.example.pathwisebackend.Enum.SessionStatus;
import com.example.pathwisebackend.Models.CoachingSession;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.CoachingSessionRepository;
import com.example.pathwisebackend.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CoachingSessionService {

    private final CoachingSessionRepository sessionRepository;
    private final UserRepository userRepository;

    public CoachingSessionService(CoachingSessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    // Coach creates a session
    public CoachingSession createSession(SessionDTO dto) {
        User coach = userRepository.findById(dto.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found"));
        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        CoachingSession session = new CoachingSession();
        session.setCoach(coach);
        session.setStudent(student);
        session.setSessionType(dto.getSessionType());
        session.setDate(dto.getDate());
        session.setStartTime(dto.getStartTime());
        session.setEndTime(dto.getStartTime().plusMinutes(dto.getDuration())); // calculate end time
        session.setSessionMode(dto.getSessionMode());
        session.setStatus(SessionStatus.SCHEDULED); // default status when created

        return sessionRepository.save(session);
    }

    // Student updates session status
    public CoachingSession updateSessionStatus(Long sessionId, Long studentId, SessionStatus status) {
        CoachingSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if (!session.getStudent().getId().equals(studentId)) {
            throw new RuntimeException("Only the assigned student can update session status");
        }

        session.setStatus(status);
        return sessionRepository.save(session);
    }

    public List<CoachingSession> getAllSessions() {
        return sessionRepository.findAll();
    }
}
