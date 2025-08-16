package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.CommentDTO;

import java.util.List;

public interface ICommentService {
    CommentDTO addComment(Long postId, Long userId, String text);
    List<CommentDTO> getCommentsByPost(Long postId);
    void deleteComment(Long commentId, Long userId);
}
