package com.example.pathwisebackend.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageResponseDTO {
    private Long id;
    private String content;
    private Long senderId;
    private String senderName;
    private Long receiverId;
    private String receiverName;
    private LocalDateTime timestamp;
}

