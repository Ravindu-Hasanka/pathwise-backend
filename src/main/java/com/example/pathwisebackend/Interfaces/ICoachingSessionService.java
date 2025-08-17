package com.example.pathwisebackend.Interfaces;

import com.example.pathwisebackend.DTO.SessionDTO;
import com.example.pathwisebackend.Models.CoachingSession;

import java.time.LocalDateTime;
import java.util.List;

public interface ICoachingSessionService {
    CoachingSession scheduleSession(SessionDTO dto);
    List<CoachingSession> getCoachSessions(Long coachId);
    public SessionDTO confirm(Long id);
    public SessionDTO complete(Long id);
    public SessionDTO cancel(Long id);
    public List<SessionDTO> listForCoachByDate(Long coachId, LocalDateTime date);
}
