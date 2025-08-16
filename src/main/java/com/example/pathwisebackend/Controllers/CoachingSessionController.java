package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.DTO.SessionDTO;
import com.example.pathwisebackend.Models.CoachingSession;
import com.example.pathwisebackend.Services.ICoachingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/session")
@RequiredArgsConstructor
public class CoachingSessionController {

    private final ICoachingSessionService sessionService;


    @GetMapping("/coach/{coachId}")
    public ResponseEntity<List<CoachingSession>> getCoachSessions(@PathVariable Long coachId) {
        return ResponseEntity.ok(sessionService.getCoachSessions(coachId));
    }

    @PostMapping("/{id}/confirm")  public ResponseEntity<SessionDTO> confirm(@PathVariable Long id){ return ResponseEntity.ok(sessionService.confirm(id)); }
    @PostMapping("/{id}/cancel")   public ResponseEntity<SessionDTO> cancel(@PathVariable Long id){ return ResponseEntity.ok(sessionService.cancel(id)); }
    @PostMapping("/{id}/complete") public ResponseEntity<SessionDTO> complete(@PathVariable Long id){ return ResponseEntity.ok(sessionService.complete(id)); }

    @GetMapping("/{coachId}")
    public ResponseEntity<List<SessionDTO>> listForCoachByDate(
            @PathVariable Long coachId,
            @RequestParam LocalDateTime date) {
        return ResponseEntity.ok(sessionService.listForCoachByDate(coachId, date));
    }
}
