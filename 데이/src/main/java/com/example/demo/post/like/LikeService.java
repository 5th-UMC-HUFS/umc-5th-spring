package com.example.demo.post.like;

import com.example.demo.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import com.example.demo.post.PostEntity;
import com.example.demo.post.PostRepository;
import com.example.demo.user.UserRepository;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addLike(Long userId, Long postId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        LikeEntity existingLike = likeRepository.findByUserAndPost(user, post);
        if(existingLike == null) {
            LikeEntity likeEntity = new LikeEntity(user, post);
            likeRepository.save(likeEntity);
            post.setLikeCount(post.getLikeCount() + 1);
            postRepository.save(post);
        }
    }

    @Transactional
    public void removeLike(Long userId, Long postId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        LikeEntity likeEntity = likeRepository.findByUserAndPost(user, post);
        if(likeEntity != null) {
            likeRepository.delete(likeEntity);
            post.setLikeCount(post.getLikeCount() - 1);
            postRepository.save(post);
        }
    }
}
