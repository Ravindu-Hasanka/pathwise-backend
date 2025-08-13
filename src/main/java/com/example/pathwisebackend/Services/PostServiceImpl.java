package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.AuthorDTO;
import com.example.pathwisebackend.DTO.CommentDTO;
import com.example.pathwisebackend.DTO.LikeDTO;
import com.example.pathwisebackend.DTO.PostDTO;
import com.example.pathwisebackend.Models.Post;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.ConnectionRepository;
import com.example.pathwisebackend.Repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService {

    private final PostRepository postRepository;
    private final ConnectionRepository connectionRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long id, Post updatedPost) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setContent(updatedPost.getContent());
                    post.setImageUrl(updatedPost.getImageUrl());
                    post.setCreatedAt(updatedPost.getCreatedAt());
                    post.setAuthor(updatedPost.getAuthor());
                    return postRepository.save(post);
                })
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }


    public List<PostDTO> getPostsByAuthors(Long userId) {
        // 1. Get IDs of connected users
        List<User> connections = connectionRepository.findAllConnectedUsers(userId);
        List<Long> authorIds = connections.stream()
                .map(User::getId)
                .toList();

        // 2. Fetch posts by connected authors
        List<Post> posts = postRepository.findByAuthorIdInOrderByCreatedAtDesc(authorIds);

        // 3. Map to PostDTO safely
        List<PostDTO> postDTOs = posts.stream()
                .map(post -> {
                    // Map author
                    AuthorDTO authorDTO = new AuthorDTO(
                            post.getAuthor().getId(),
                            post.getAuthor().getName(),
                            post.getAuthor().getEmail(),
                            post.getAuthor().getRole()
                    );

                    // Map comments to DTO
                    List<CommentDTO> commentDTOs = post.getComments().stream()
                            .map(c -> new CommentDTO(
                                    c.getId(),
                                    c.getText(),
                                    new AuthorDTO(
                                            c.getAuthor().getId(),
                                            c.getAuthor().getName(),
                                            c.getAuthor().getEmail(),
                                            c.getAuthor().getRole()
                                    ),
                                    c.getCreatedAt()
                            ))
                            .toList();

                    // Map likes to DTO
                    List<LikeDTO> likeDTOs = post.getLikes().stream()
                            .map(l -> new LikeDTO(
                                    l.getId(),
                                    new AuthorDTO(
                                            l.getUser().getId(),
                                            l.getUser().getName(),
                                            l.getUser().getEmail(),
                                            l.getUser().getRole()
                                    ),
                                    l.getCreatedAt()
                            ))
                            .toList();

                    // Create PostDTO
                    return new PostDTO(
                            post.getId(),
                            post.getTitle(),
                            post.getContent(),
                            post.getImageUrl(),
                            post.getCreatedAt(),
                            post.getUpdatedAt(),
                            authorDTO,
                            commentDTOs,
                            likeDTOs
                    );
                })
                .toList();

        return postDTOs;
    }



}
