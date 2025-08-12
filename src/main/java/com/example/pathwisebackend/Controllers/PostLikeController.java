package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.Services.IPostLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class PostLikeController {

    private final IPostLikeService likeService;
    public PostLikeController(IPostLikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<String> toggleLike(@PathVariable Long postId,
                                             @RequestParam Long userId) {
        return ResponseEntity.ok(likeService.toggleLike(postId, userId));
    }

    @GetMapping("/post/{postId}/count")
    public ResponseEntity<Long> getLikeCount(@PathVariable Long postId) {
        return ResponseEntity.ok(likeService.getLikeCount(postId));
    }
}
