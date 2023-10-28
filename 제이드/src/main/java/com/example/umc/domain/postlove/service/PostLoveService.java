package com.example.umc.domain.postlove.service;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.repository.MemberRepository;
import com.example.umc.domain.post.entity.Post;
import com.example.umc.domain.post.repository.PostRepository;
import com.example.umc.domain.postlove.dto.PostLoveRequestDto;
import com.example.umc.domain.postlove.entity.PostLove;
import com.example.umc.domain.postlove.repository.PostLoveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLoveService {
    private final PostLoveRepository postLoveRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void toggleLove(Long postId, PostLoveRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("등록되지 않은 회원에 대한 접근입니다."));
        Post targetPost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("등록되지 않은 게시물에 대한 접근입니다."));
        Optional<PostLove> postLoveOptional = postLoveRepository.findByMemberAndTargetPost(member, targetPost);

        if(postLoveOptional.isPresent()) {
            PostLove postLove = postLoveOptional.get();
            targetPost.removeLove(postLove);
            postLoveRepository.delete(postLove);
        }
        else {
            PostLove postLove = new PostLove();
            postLove.setMember(member);
            postLove.setTargetPost(targetPost);
            targetPost.addLove(postLove);
            postLoveRepository.save(postLove);
        }
    }
    public Boolean isLoved(Long postId, PostLoveRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("등록되지 않은 회원에 대한 접근입니다."));
        Post targetPost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("등록되지 않은 게시물에 대한 접근입니다."));
        Optional<PostLove> postLoveOptional = postLoveRepository.findByMemberAndTargetPost(member, targetPost);

        return postLoveOptional.isPresent();
    }

}
