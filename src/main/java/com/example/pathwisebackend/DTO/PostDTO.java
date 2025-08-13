package com.example.pathwisebackend.DTO;

import lombok.Data;

@Data
public class PostDTO {
    private String title;
    private String content;
    private String imageUrl;
    private Long authorId;
}
