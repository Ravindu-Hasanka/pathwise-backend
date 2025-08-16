package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.Models.Like;
import com.example.pathwisebackend.Models.Post;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.PostLikeRepository;
import com.example.pathwisebackend.Repositories.PostRepository;
import com.example.pathwisebackend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements IPostLikeService {

    private final PostLikeRepository likeRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    @Override
    public String toggleLike(Long postId, Long userId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        return likeRepo.findByPostPostIdAndUserId(postId, userId)
                .map(existingLike -> {
                    likeRepo.delete(existingLike);
                    return "Unliked";
                })
                .orElseGet(() -> {
                    Like newLike = new Like();
                    newLike.setPost(post);
                    newLike.setUser(user);
                    likeRepo.save(newLike);
                    return "Liked";
                });
    }

    @Override
    public long getLikeCount(Long postId) {
        return likeRepo.countByPostPostId(postId);
    }
}
