package com.example.pathwisebackend.Models;

import com.example.pathwisebackend.Enum.ConnectionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "connections")
@Data
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long connectionId;

    @Enumerated(EnumType.STRING)
    private ConnectionStatus status = ConnectionStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;

    @ManyToOne
    @JoinColumn(name = "requested_id", nullable = false)
    private User requestedUser;

    private LocalDateTime requestedAt;
    private LocalDateTime updatedAt;
}