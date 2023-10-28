package com.example.umc.domain.postlove.repository;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.post.entity.Post;
import com.example.umc.domain.postlove.entity.PostLove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLoveRepository extends JpaRepository<PostLove, Long> {
    Optional<PostLove> findByMemberAndTargetPost(Member member, Post targetPost);
}
