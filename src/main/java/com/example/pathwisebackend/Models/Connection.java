package com.example.pathwisebackend.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "connections")
@Data
public class Connection {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long connectionId;

    @Enumerated(EnumType.STRING)
    private ConnectionStatus status = ConnectionStatus.PENDING;


    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne @JoinColumn(name = "requested_user_id")
    private User requestedUser;

    private LocalDateTime requestedAt;
    private LocalDateTime updatedAt;
}

