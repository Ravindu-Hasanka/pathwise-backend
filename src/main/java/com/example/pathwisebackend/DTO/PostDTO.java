package com.example.pathwisebackend.DTO;

import com.example.pathwisebackend.DTO.AuthorDTO;
import com.example.pathwisebackend.Models.Comment;
import com.example.pathwisebackend.Models.PostLike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AuthorDTO author;
    private List<CommentDTO> comments;
    private List<LikeDTO> likes;
}
