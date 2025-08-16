package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.AuthorDTO;
import com.example.pathwisebackend.DTO.CommentDTO;
import com.example.pathwisebackend.DTO.LikeDTO;
import com.example.pathwisebackend.DTO.PostDTO;
import com.example.pathwisebackend.Interfaces.IPostService;
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
                    post.setCaption(updatedPost.getCaption());
                    post.setContent(updatedPost.getContent());
                    post.setContentType(updatedPost.getContentType());
                    post.setCreatedBy(updatedPost.getCreatedBy());
                    post.setCreatedAt(updatedPost.getCreatedAt());
                    return postRepository.save(post);
                })
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }


    public List<PostDTO> getPostsByAuthors(Long userId) {
        List<User> connections = connectionRepository.findAllConnectedUsers(userId);
        List<Long> authorIds = connections.stream()
                .map(User::getUserId)
                .toList();

        List<Post> posts = postRepository.findByCreatedByIdInOrderByCreatedAtDesc(authorIds);

        List<PostDTO> postDTOs = posts.stream()
                .map(post -> {
                    AuthorDTO authorDTO = new AuthorDTO(
                            post.getCreatedBy().getUserId(),
                            post.getCreatedBy().getName(),
                            post.getCreatedBy().getEmail(),
                            post.getCreatedBy().getRole()
                    );

                    List<CommentDTO> commentDTOs = post.getComments().stream()
                            .map(c -> new CommentDTO(
                                    c.getCommentId(),
                                    c.getComment(),
                                    new AuthorDTO(
                                            c.getAuthor().getUserId(),
                                            c.getAuthor().getName(),
                                            c.getAuthor().getEmail(),
                                            c.getAuthor().getRole()
                                    ),
                                    c.getCreatedAt()
                            ))
                            .toList();

                    List<LikeDTO> likeDTOs = post.getLikes().stream()
                            .map(l -> new LikeDTO(
                                    l.getLikeId(),
                                    new AuthorDTO(
                                            l.getCreatedBy().getUserId(),
                                            l.getCreatedBy().getName(),
                                            l.getCreatedBy().getEmail(),
                                            l.getCreatedBy().getRole()
                                    ),
                                    l.getCreatedAt()
                            ))
                            .toList();

                    return new PostDTO(
                            post.getPostId(),
                            post.getCaption(),
                            post.getContent(),
                            post.getContentType(),
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
