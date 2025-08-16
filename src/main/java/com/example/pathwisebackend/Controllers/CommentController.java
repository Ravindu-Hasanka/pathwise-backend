package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.Models.Comment;
import com.example.pathwisebackend.Interfaces.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final ICommentService commentService;
    @PostMapping("/post/{postId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId,
                                              @RequestParam Long userId,
                                              @RequestBody String text) {
        return ResponseEntity.ok(commentService.addComment(postId, userId, text));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId,
                                              @RequestParam Long userId) {
        commentService.deleteComment(commentId, userId);
        return ResponseEntity.noContent().build();
    }
}
