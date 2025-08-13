package com.example.pathwisebackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {
    private Long id;
    private AuthorDTO user;
    private LocalDateTime createdAt;
}
