package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.Models.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    Post createPost(Post post);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);
}
