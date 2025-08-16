package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.SessionDTO;
import com.example.pathwisebackend.Models.CoachingSession;
import com.example.pathwisebackend.Models.SessionStatus;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.CoachingSessionRepository;
import com.example.pathwisebackend.Repositories.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachingSessionServiceImpl implements  ICoachingSessionService {

    private final CoachingSessionRepository sessionRepo;
    private final UserRepository userRepo;

    private SessionDTO map(CoachingSession cs) {
        return new SessionDTO(
                cs.getCoach().getId(),
                cs.getStudent().getId(),
                cs.getSessionType(),
                cs.getStartTime(),
                Duration.between(cs.getStartTime(), cs.getEndTime()).toMinutes(),
                cs.getSessionMode(),
                cs.getStatus()
        );
    }

    private void ensureNoOverlap(Long coachId, LocalDateTime start, LocalDateTime end, Long ignoreId) {
        if (sessionRepo.coachHasOverlap(coachId, start, end, ignoreId))
            throw new ValidationException("Coach already has a session in this time range");
    }

    @Override
    public CoachingSession scheduleSession(SessionDTO dto) {

        User coach = userRepo.findById(dto.getCoachId()).orElseThrow();
        User student = userRepo.findById(dto.getStudentId()).orElseThrow();

        ensureNoOverlap(coach.getId(), dto.getStartTime(), dto.getStartTime().plusMinutes(dto.getDuration()), null);
        CoachingSession session = new CoachingSession();
        session.setCoach(coach);
        session.setStudent(student);
        session.setSessionMode(dto.getSessionMode());
        session.setSessionType(dto.getSessionType());
        session.setStartTime(dto.getStartTime());
        session.setEndTime(dto.getStartTime().plusMinutes(dto.getDuration()));
        session.setStatus(SessionStatus.SCHEDULED);

        return sessionRepo.save(session);
    }

    @Override
    public List<CoachingSession> getCoachSessions(Long coachId) {
        return sessionRepo.findByCoachId(coachId);
    }

    @Override
     public SessionDTO confirm(Long id) {
        var cs = sessionRepo.findById(id).orElseThrow();
        cs.setStatus(SessionStatus.CONFIRMED);
        return map(cs);
    }

    @Override
     public SessionDTO cancel(Long id) {
        var cs = sessionRepo.findById(id).orElseThrow();
        cs.setStatus(SessionStatus.CANCELLED);
        return map(cs);
    }

    @Override
     public SessionDTO complete(Long id) {
        var cs = sessionRepo.findById(id).orElseThrow();
        cs.setStatus(SessionStatus.COMPLETED);
        return map(cs);
    }

@Override
    public List<SessionDTO> listForCoachByDate(Long coachId, LocalDateTime date) {
        LocalDateTime start = date.toLocalDate().atStartOfDay();
        LocalDateTime end = start.plusDays(1);
        return sessionRepo.findByCoachIdAndStartTimeBetweenOrderByStartTimeAsc(coachId, start,end)
                .stream()
                .map(this::map)
                .toList();
    }
}

