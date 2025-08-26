package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.ConnectionDTO;
import com.example.pathwisebackend.Interfaces.INetworkService;
import com.example.pathwisebackend.Models.Connection;
import com.example.pathwisebackend.Enum.ConnectionStatus;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.ConnectionRepository;
import com.example.pathwisebackend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NetworkServiceImpl implements INetworkService {
    private final UserRepository userRepo;
    private final ConnectionRepository connRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public Connection requestConnection(Long senderId, Long userId) {
        User user = userRepo.findById(senderId).orElseThrow();
        User requestedUser = userRepo.findById(userId).orElseThrow();

        Connection conn = new Connection();
        conn.setReceiver(requestedUser);
        conn.setInitiator(user);
        conn.setRequestedAt(LocalDateTime.now());
        return connRepo.save(conn);
    }
    public Connection acceptConnection(Long connectionId) {
        Connection connection = connRepo.findById(connectionId).orElseThrow();

        connection.setStatus(ConnectionStatus.CONNECTED);
        connection.setUpdatedAt(LocalDateTime.now());
        return connRepo.save(connection);
    }

    public Connection ignoreConnectionReq(Long connectionId) {
        Connection connection = connRepo.findById(connectionId).orElseThrow();

        connection.setStatus(ConnectionStatus.IGNORED);
        connection.setUpdatedAt(LocalDateTime.now());
        return connRepo.save(connection);
    }

    public List<ConnectionDTO> getAllConnectedUsers(Long userId) {

        List<Connection> requests= connRepo.findAllConnections(userId);
        return requests.stream()
                .map(c -> {
                    User otherUser;
                    if (c.getInitiator().getId().equals(userId)) {
                        otherUser = c.getReceiver();
                    } else {
                        otherUser = c.getInitiator();
                    }
                    ConnectionDTO dto = new ConnectionDTO();
                    dto.setConnectionId(c.getConnectionId());
                    dto.setRequestedUserId(otherUser.getId());
                    dto.setRequestedUserName(otherUser.getName());
                    dto.setJobRole(otherUser.getRole().toString());
                    dto.setEmail(otherUser.getEmail());
                    dto.setRequestedAt(c.getRequestedAt());
                    return dto;
                })
                .toList();

    }
    public List<ConnectionDTO> getConnectionRequests(Long userId) {

        List<Connection> requests= connRepo.findConnectionRequests(userId);
        return requests.stream()
                .map(c -> {
                    User requester = c.getInitiator();
                    ConnectionDTO dto = new ConnectionDTO();
                    dto.setConnectionId(c.getConnectionId());
                    dto.setRequestedUserId(requester.getId());
                    dto.setRequestedUserName(requester.getName());
                    dto.setJobRole(requester.getRole().toString());
                    dto.setEmail(requester.getEmail());
                    dto.setRequestedAt(c.getRequestedAt());
                    return dto;
                })
                .toList();
    }

}