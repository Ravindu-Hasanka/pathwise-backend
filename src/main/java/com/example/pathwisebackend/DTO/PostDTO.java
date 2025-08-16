package com.example.pathwisebackend.DTO;

import com.example.pathwisebackend.Enum.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long postId;
    private String caption;
    private String content;
    private ContentType contentType;
    private Date createdAt;
    private Date updatedAt;
    private AuthorDTO createdBy;
    private List<CommentDTO> comments;
    private List<LikeDTO> likes;
}
