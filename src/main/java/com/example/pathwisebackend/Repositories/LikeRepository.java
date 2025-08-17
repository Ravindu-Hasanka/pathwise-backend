package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostPostIdAndCreatedById(Long postId, Long userId);
    long countByPostPostId(Long postId);
}
