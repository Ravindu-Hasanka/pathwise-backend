package com.example.pathwisebackend.Interfaces;

import com.example.pathwisebackend.DTO.PostDTO;
import com.example.pathwisebackend.Models.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    Post createPost(Post post);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);
    List<PostDTO> getPostsByAuthors(Long userId);
}
