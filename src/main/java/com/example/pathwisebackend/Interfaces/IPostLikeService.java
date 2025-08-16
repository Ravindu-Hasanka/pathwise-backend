package com.example.pathwisebackend.Interfaces;

public interface IPostLikeService {
    String toggleLike(Long postId, Long userId);
    long getLikeCount(Long postId);
}
