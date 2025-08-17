package com.example.pathwisebackend.Interfaces;

import com.example.pathwisebackend.DTO.CommentDTO;
import com.example.pathwisebackend.Models.Comment;
import java.util.List;

public interface ICommentService {
    CommentDTO addComment(Long postId, Long userId, String text);
    List<CommentDTO> getCommentsByPost(Long postId);
    void deleteComment(Long commentId, Long userId);
}
