package com.example.pathwisebackend.Services;

import java.util.Optional;

public interface IPostLikeService {
    String toggleLike(Long postId, Long userId);
    long getLikeCount(Long postId);
}
