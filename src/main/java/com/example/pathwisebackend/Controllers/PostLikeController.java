package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.Interfaces.IPostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class PostLikeController {

    private final IPostLikeService likeService;
    @PostMapping("/post/{postId}")
    public ResponseEntity<String> toggleLike(@PathVariable Long postId,
                                             @RequestParam Long userId) {
        return ResponseEntity.ok(likeService.toggleLike(postId, userId));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Long> getLikeCount(@PathVariable Long postId) {
        return ResponseEntity.ok(likeService.getLikeCount(postId));
    }
}
