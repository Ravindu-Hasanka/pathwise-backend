package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostPostIdAndUserId(Long postId, Long userId);
    long countByPostPostId(Long postId);
}
