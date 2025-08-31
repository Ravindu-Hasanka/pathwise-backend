package com.example.pathwisebackend.Interfaces;

import com.example.pathwisebackend.DTO.ConnectionDTO;
import com.example.pathwisebackend.DTO.User.UserDTO;
import com.example.pathwisebackend.Models.Connection;

import java.util.List;

public interface INetworkService {
    Connection requestConnection(Long userId, Long targetId);
    Connection acceptConnection( Long connectionId);
    Connection ignoreConnectionReq(Long connectionId);
    List<ConnectionDTO> getAllConnectedUsers(Long userId);
    List<ConnectionDTO> getConnectionRequests(Long userId);
    List<ConnectionDTO> getSentRequests(Long userId);
    List<UserDTO> getSuggestions(Long userId) ;
}
