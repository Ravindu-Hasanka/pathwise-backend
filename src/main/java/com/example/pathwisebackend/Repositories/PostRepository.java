package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatedById(Long userId);
    List<Post> findByCreatedByIdInOrderByCreatedAtDesc(List<Long> authorIds);
}
