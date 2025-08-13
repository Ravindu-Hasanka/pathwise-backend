package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.Models.Connection;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Services.NetworkServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/network")
@RequiredArgsConstructor
public class NetworkController {
    private final NetworkServiceImpl networkService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return networkService.createUser(user);
    }

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
    public List<User> getConnectedUsers(@PathVariable Long userId) {
        return networkService.getAllConnectedUsers(userId);
    }
}
