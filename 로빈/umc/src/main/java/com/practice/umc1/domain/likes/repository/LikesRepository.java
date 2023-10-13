package com.practice.umc1.domain.likes.repository;

import com.practice.umc1.domain.likes.entity.Likes;
import com.practice.umc1.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByPost(Post post);
}
