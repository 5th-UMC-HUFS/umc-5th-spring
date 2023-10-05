package com.example.umc.Domain.Post.Repository;

import com.example.umc.Domain.Post.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByMemberId(Long memberId);

}
