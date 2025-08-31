package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.DTO.ConnectionDTO;
import com.example.pathwisebackend.DTO.User.UserDTO;
import com.example.pathwisebackend.Interfaces.INetworkService;
import com.example.pathwisebackend.Models.Connection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/network")
@RequiredArgsConstructor
public class NetworkController {
    private final INetworkService networkService;

    @PostMapping("/connect/{userId}/{targetId}")
    public Connection connect(@PathVariable Long userId, @PathVariable Long targetId) {
        return networkService.requestConnection(userId, targetId);
    }
    @PostMapping("/accept/{connectionId}")
    public Connection acceptConnection(@PathVariable Long connectionId) {
        return networkService.acceptConnection(connectionId);
    }

    @PostMapping("/ignore/{connectionId}")
    public Connection rejectConnection(@PathVariable Long connectionId) {
        return networkService.ignoreConnectionReq(connectionId);
    }
    @GetMapping("/{userId}/connections")
    public List<ConnectionDTO> getConnectedUsers(@PathVariable Long userId) {
        return networkService.getAllConnectedUsers(userId);
    }

    @GetMapping("/{userId}/requests")
    public List<ConnectionDTO> getConnectionRequests(@PathVariable Long userId) {
        return networkService.getConnectionRequests(userId);
    }
    @GetMapping("/{userId}/sentRequests")
    public List<ConnectionDTO> getSentRequests(@PathVariable Long userId) {
        return networkService.getSentRequests(userId);
    }
    @GetMapping("/{userId}/suggestions")
    public ResponseEntity<List<UserDTO>> getSuggestions(@PathVariable Long userId) {
        return ResponseEntity.ok(networkService.getSuggestions(userId));
    }
}
