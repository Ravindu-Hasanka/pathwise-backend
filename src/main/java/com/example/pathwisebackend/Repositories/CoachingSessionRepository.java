package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.CoachingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CoachingSessionRepository extends JpaRepository<CoachingSession, Long> {
    List<CoachingSession> findByCoachId(Long coachId);
    List<CoachingSession> findByStudentId(Long studentId);

    @Query("""
     select (count(cs) > 0) from CoachingSession cs
     where cs.coach.userId = :coachId
       and cs.status <> "CANCELLED"
       and cs.startTime < :endT and cs.endTime > :startT
       and (:ignoreId is null or cs.sessionId <> :ignoreId)
  """)
    boolean coachHasOverlap(@Param("coachId") Long coachId,
                            @Param("startT") LocalDateTime startT,
                            @Param("endT") LocalDateTime endT,
                            @Param("ignoreId") Long ignoreId);



    List<CoachingSession> findByCoachIdAndStartTimeBetweenOrderByStartTimeAsc(
            Long coachId, LocalDateTime start, LocalDateTime end);

}
