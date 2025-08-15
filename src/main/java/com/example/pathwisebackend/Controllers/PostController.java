package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.DTO.PostDTO;
import com.example.pathwisebackend.Models.Post;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.UserRepository;
import com.example.pathwisebackend.Services.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final IPostService postService;
    private final UserRepository userRepository;
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Post createPost(@RequestBody PostDTO postRequest) {
        User author = userRepository.findById(postRequest.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setCaption(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setContentType(postRequest.getContentType());
        post.setCreatedBy(author);
        post.setCreatedAt(LocalDateTime.now());

        return postService.createPost(post);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        try {
            return ResponseEntity.ok(postService.updatePost(id, post));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/activity-feed/{userId}")
    public List<PostDTO> getPostsByAuthors(@PathVariable Long userId) {
        return postService.getPostsByAuthors(userId);
    }
}
