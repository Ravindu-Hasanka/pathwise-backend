package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.Models.Comment;
import com.example.pathwisebackend.Models.Post;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.CommentRepository;
import com.example.pathwisebackend.Repositories.PostRepository;
import com.example.pathwisebackend.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo, UserRepository userRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Comment addComment(Long postId, Long userId, String text) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setAuthor(user);
        comment.setText(text);

        return commentRepo.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepo.findByPostId(postId);
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
