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
    @JoinColumn(name = "initiator_id", nullable = false)
    private User initiator;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    private LocalDateTime requestedAt;
    private LocalDateTime updatedAt;
}