package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.Models.Comment;
import java.util.List;

public interface ICommentService {
    Comment addComment(Long postId, Long userId, String text);
    List<Comment> getCommentsByPost(Long postId);
    void deleteComment(Long commentId, Long userId);
}
