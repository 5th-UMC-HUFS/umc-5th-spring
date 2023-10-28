package com.example.umc.domain.post.repository;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // findBy[조건+필드] 형식으로 오버로딩 가능
    List<Post> findByAuthor(Member author);
}