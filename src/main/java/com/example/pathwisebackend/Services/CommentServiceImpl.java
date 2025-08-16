package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.AuthorDTO;
import com.example.pathwisebackend.DTO.CommentDTO;
import com.example.pathwisebackend.Models.Comment;
import com.example.pathwisebackend.Models.Post;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.CommentRepository;
import com.example.pathwisebackend.Repositories.PostRepository;
import com.example.pathwisebackend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    private CommentDTO map(Comment c) {
        User u = c.getAuthor();

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(u.getId());
        authorDTO.setName(u.getName());
        authorDTO.setEmail(u.getEmail());
        authorDTO.setRole(u.getRole());

        return new CommentDTO(
                c.getCommentId(),
                c.getComment(),
                authorDTO,
                c.getCreatedAt()
        );
    }
    @Override
    public CommentDTO addComment(Long postId, Long userId, String text) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setAuthor(user);
        comment.setComment(text);

        Comment savedComment = commentRepo.save(comment);
        return map(savedComment);
    }

    @Override
    public List<CommentDTO> getCommentsByPost(Long postId) {
        return commentRepo.findByPostPostId(postId)
                .stream()
                .map(this::map)
                .toList();
    }



    @Override
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("You can only delete your own comments");
        }

        commentRepo.delete(comment);
    }
}
