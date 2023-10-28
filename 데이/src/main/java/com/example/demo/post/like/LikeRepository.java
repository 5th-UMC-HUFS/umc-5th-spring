package com.example.demo.post.like;

import com.example.demo.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.post.PostEntity;
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    LikeEntity findByUserAndPost(UserEntity user, PostEntity post);
}

