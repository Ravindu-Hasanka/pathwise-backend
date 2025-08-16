package com.example.pathwisebackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private String contentType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AuthorDTO author;

}
