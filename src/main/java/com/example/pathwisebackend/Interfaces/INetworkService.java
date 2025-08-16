package com.example.pathwisebackend.Interfaces;

import com.example.pathwisebackend.DTO.ConnectionDTO;
import com.example.pathwisebackend.Models.Connection;
import com.example.pathwisebackend.Models.User;

import java.util.List;

public interface INetworkService {
    Connection requestConnection(Long userId, Long targetId);
    Connection acceptConnection( Long connectionId);
    Connection ignoreConnectionReq(Long connectionId);
    List<ConnectionDTO> getAllConnectedUsers(Long userId);
    List<ConnectionDTO> getConnectionRequests(Long userId);
}
