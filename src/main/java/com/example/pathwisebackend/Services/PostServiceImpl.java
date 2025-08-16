package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.AuthorDTO;
import com.example.pathwisebackend.DTO.CommentDTO;
import com.example.pathwisebackend.DTO.PostDTO;
import com.example.pathwisebackend.Models.Post;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.ConnectionRepository;
import com.example.pathwisebackend.Repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                    post.setCaption(updatedPost.getCaption());
                    post.setContent(updatedPost.getContent());
                    post.setContentType(updatedPost.getContentType());
                    post.setCreatedAt(updatedPost.getCreatedAt());
                    post.setCreatedBy(updatedPost.getCreatedBy());
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
        List<Long> authorIds = new ArrayList<>(
                connections.stream()
                        .map(User::getId)
                        .toList()
        );
        authorIds.add(userId);
        // 2. Fetch posts by connected authors
        List<Post> posts = postRepository.findByCreatedByIdInOrderByCreatedAtDesc(authorIds);

        // 3. Map to PostDTO safely

        return posts.stream()
                .map(post -> {
                    // Map author
                    AuthorDTO authorDTO = new AuthorDTO(
                            post.getCreatedBy().getId(),
                            post.getCreatedBy().getName(),
                            post.getCreatedBy().getEmail(),
                            post.getCreatedBy().getRole()
                    );

                    // Map comments to DTO
                    List<CommentDTO> commentDTOs = post.getComments().stream()
                            .map(c -> new CommentDTO(
                                    c.getCommentId(),
                                    c.getComment(),
                                    new AuthorDTO(
                                            c.getAuthor().getId(),
                                            c.getAuthor().getName(),
                                            c.getAuthor().getEmail(),
                                            c.getAuthor().getRole()
                                    ),
                                    c.getCreatedAt()
                            ))
                            .toList();


                    // Create PostDTO
                    return new PostDTO(
                            post.getPostId(),
                            post.getCaption(),
                            post.getContent(),
                            post.getContentType(),
                            post.getCreatedAt(),
                            post.getUpdatedAt(),
                            authorDTO,
                            commentDTOs
                    );
                })
                .toList();
    }



}
