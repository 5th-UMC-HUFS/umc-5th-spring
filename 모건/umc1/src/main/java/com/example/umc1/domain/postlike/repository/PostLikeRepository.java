package com.example.umc1.domain.postlike.repository;

import com.example.umc1.domain.member.entity.Member;
import com.example.umc1.domain.post.entity.Post;
import com.example.umc1.domain.postlike.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByMemberAndTargetPost(Member member, Post targetPost);

    List<PostLike> findByMember(Member member);

    List<PostLike> findByTargetPost(Post targetPost);
}
