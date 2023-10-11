package com.example.umc.domain.postlike.service;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.repository.MemberRepository;
import com.example.umc.domain.post.entity.Post;
import com.example.umc.domain.post.repository.PostRepository;
import com.example.umc.domain.postlike.dto.PostLikeRequestDto;
import com.example.umc.domain.postlike.dto.PostLikeResponseDto;
import com.example.umc.domain.postlike.entity.PostLike;
import com.example.umc.domain.postlike.repository.PostLikeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    // 좋아요
    // 처음 누르면 좋아요 entity 생성하고 저장
    // 이미 좋아요 entity가 있으면 삭제
    @Transactional
    public boolean postLike(Long postId, PostLikeRequestDto postLikeRequestDto){
        // member와 post가 있는지 확인
        Optional<Member> optionalMember = memberRepository.findById(postLikeRequestDto.getMemberId());
        Optional<Post> optionalPost = postRepository.findById(postId);

        optionalMember
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));

        optionalPost
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        Optional<PostLike> optionalPostLike = postLikeRepository.findByPostIdAndMemberId(postId, postLikeRequestDto.getMemberId());

        Member member = optionalMember.get();
        Post post = optionalPost.get();

        // 좋아요가 있으면 member와 post와의 연결을 끊고 delete
        if(optionalPostLike.isPresent()){
            PostLike postLike = optionalPostLike.get();
            member.disLike(postLike);
            post.disLike(postLike);
            postLikeRepository.delete(optionalPostLike.get());

            log.info("id:{} 게시글, id:{} 회원 좋아요취소", post.getId(), member.getId());

            return false;
        } else {
            PostLike postLike = PostLike.builder()
                    .member(optionalMember.get())
                    .post(optionalPost.get())
                    .build();

            member.like(postLike);
            post.like(postLike);
            postLikeRepository.save(postLike);

            log.info("id:{} 게시글, id:{} 회원 좋아요", post.getId(), member.getId());

            return true;
        }

    }

    // 회원 좋아요 리스트
    public List<PostLikeResponseDto> getPostsLikeByMemberId(Long memberId){

        Optional<Member> optionalMember = memberRepository.findById(memberId);

        optionalMember
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

        log.info("id:{} 회원 좋아요 리스트", optionalMember.get().getId());

        return postLikeRepository.findByMemberId(memberId)
                .stream()
                .map(PostLikeResponseDto::of)
                .collect(Collectors.toList());
    }


    // 게시글 좋아요 리스트
    public List<PostLikeResponseDto> getPostsLikeByPostId(Long postId){
        Optional<Post> optionalPost = postRepository.findById(postId);

        optionalPost
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

        log.info("id:{} 게시글 좋아요 리스트", optionalPost.get().getId());

        return postLikeRepository.findByMemberId(postId)
                .stream()
                .map(PostLikeResponseDto::of)
                .collect(Collectors.toList());
    }


}
