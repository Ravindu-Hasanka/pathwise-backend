package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.DTO.SessionDTO;
import com.example.pathwisebackend.Enum.SessionStatus;
import com.example.pathwisebackend.Models.CoachingSession;
import com.example.pathwisebackend.Services.CoachingSessionService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sessions")
@CrossOrigin
public class CoachingSessionController {

    private final CoachingSessionService sessionService;

    public CoachingSessionController(CoachingSessionService sessionService) {
        this.sessionService = sessionService;
    }

    // Coach creates session
    @PostMapping("/create")
    public ResponseEntity<CoachingSession> createSession(@RequestBody SessionDTO dto) {
        return ResponseEntity.ok(sessionService.createSession(dto));
    }

    // Student updates status
    @PutMapping("/{sessionId}/status")
    public ResponseEntity<CoachingSession> updateSessionStatus(
            @PathVariable Long sessionId,
            @RequestParam Long studentId,
            @RequestParam SessionStatus status
    ) {
        return ResponseEntity.ok(sessionService.updateSessionStatus(sessionId, studentId, status));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CoachingSession>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }
}
