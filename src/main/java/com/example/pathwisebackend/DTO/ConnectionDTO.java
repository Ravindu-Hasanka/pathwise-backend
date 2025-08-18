
package com.example.pathwisebackend.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConnectionDTO {
    private Long connectionId;
    private Long requestedUserId;
    private String requestedUserName;
    private String jobRole;
    private String email;
    private LocalDateTime requestedAt;
}

