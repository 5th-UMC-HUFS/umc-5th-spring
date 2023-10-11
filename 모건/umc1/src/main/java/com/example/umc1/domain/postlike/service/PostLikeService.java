package com.example.umc1.domain.postlike.service;

import com.example.umc1.domain.member.entity.Member;
import com.example.umc1.domain.member.repository.MemberRepository;
import com.example.umc1.domain.post.entity.Post;
import com.example.umc1.domain.post.repository.PostRepository;
import com.example.umc1.domain.postlike.dto.PostLIkeRequestDto;
import com.example.umc1.domain.postlike.dto.PostLikeResponseDto;
import com.example.umc1.domain.postlike.entity.PostLike;
import com.example.umc1.domain.postlike.repository.PostLikeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void likePost(Long postId, PostLIkeRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("회원 정보를 불러올 수 없습니다."));
        Post targetPost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 정보를 불러올 수 없습니다."));

        Optional<PostLike> postLikeOptional = postLikeRepository.findByMemberAndTargetPost(member, targetPost);

        if (postLikeOptional.isPresent()) {
            PostLike postLike = postLikeOptional.get();
            targetPost.removeLike(postLike);
            postLikeRepository.delete(postLike);
        } else {
            PostLike postLike = new PostLike();
            postLike.setMember(member);
            postLike.setTargetPost(targetPost);
            targetPost.addLike(postLike);
            postLikeRepository.save(postLike);
        }
    }

    @Transactional
    public boolean isLiked(Long postId, PostLIkeRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("회원 정보를 불러올 수 없습니다."));
        Post targetPost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 정보를 불러올 수 없습니다."));

        Optional<PostLike> postLikeOptional = postLikeRepository.findByMemberAndTargetPost(member, targetPost);

        if (postLikeOptional.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public List<PostLikeResponseDto> getLikesByMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원 정보를 불러올 수 없습니다."));
        List<PostLike> likes = postLikeRepository.findByMember(member);
        List<PostLikeResponseDto> dtos = likes.stream()
                .map(PostLikeResponseDto::of)
                .collect(Collectors.toList());
        return dtos;
    }

    public List<PostLikeResponseDto> getLikesByPost(Long postId) {
        Post targetPost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 정보를 불러올 수 없습니다."));
        List<PostLike> likes = postLikeRepository.findByTargetPost(targetPost);
        List<PostLikeResponseDto> dtos = likes.stream()
                .map(PostLikeResponseDto::of)
                .collect(Collectors.toList());
        return dtos;
    }
}
