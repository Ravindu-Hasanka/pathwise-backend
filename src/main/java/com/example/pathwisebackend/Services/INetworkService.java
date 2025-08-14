package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.Models.Connection;
import com.example.pathwisebackend.Models.User;

import java.util.List;

public interface INetworkService {
    Connection requestConnection(Long userId, Long targetId);
    Connection acceptConnection( Long connectionId);
    Connection ignoreConnectionReq(Long connectionId);
    List<User> getAllConnectedUsers(Long userId);
    List<User> getConnectionRequests(Long userId);
}
