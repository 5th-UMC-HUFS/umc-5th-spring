package com.example.umc.domain.post.service;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.repository.MemberRepository;
import com.example.umc.domain.post.dto.PostDeleteRequestDto;
import com.example.umc.domain.post.dto.PostGetResponseDto;
import com.example.umc.domain.post.dto.PostRegisterRequestDto;
import com.example.umc.domain.post.dto.PostUpdateRequestDto;
import com.example.umc.domain.post.entity.Post;
import com.example.umc.domain.post.repository.PostRepository;
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
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void registerPost(PostRegisterRequestDto postRegisterRequestDto) {

        Optional<Member> optionalMember = memberRepository.findById(postRegisterRequestDto.getMemberId());

        optionalMember
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

        Post post = Post.builder()
                .title(postRegisterRequestDto.getTitle())
                .content(postRegisterRequestDto.getContent())
                .member(optionalMember.get())
                .build();

        log.info("id:{} 회원 게시글 등록", optionalMember.get().getId());

        postRepository.save(post);

    }

    @Transactional
    public PostGetResponseDto getPost(Long postId) {

        Optional<Post> optionalPost = postRepository.findById(postId);

        optionalPost
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));

        Post post = optionalPost.get();

        log.info("id:{} 게시글 정보", post.getId());

        return PostGetResponseDto.of(post);
    }


    @Transactional
    public List<PostGetResponseDto> getPosts(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        optionalMember
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

        log.info("id:{} 회원 게시글 리스트", optionalMember.get().getId());

        return postRepository.findByMemberId(memberId)
                .stream()
                .map(PostGetResponseDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updatePost(Long postId, PostUpdateRequestDto postUpdateRequestDto) {

        Optional<Member> optionalMember = memberRepository.findById(postUpdateRequestDto.getMemberId());
        Optional<Post> optionalPost = postRepository.findById(postId);

        optionalMember
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

        optionalPost
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));

        Post post = optionalPost.get();

        if (post.getMember() == optionalMember.get()) {

            log.info("id:{} 게시글 수정", post.getId());

            post.update(postUpdateRequestDto);
        } else {
            throw new RuntimeException("권한 없음");
        }


    }

    @Transactional
    public void deletePost(Long postId, PostDeleteRequestDto postDeleteRequestDto) {
        Optional<Member> optionalMember = memberRepository.findById(postDeleteRequestDto.getMemberId());
        Optional<Post> optionalPost = postRepository.findById(postId);

        optionalMember
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

        optionalPost
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));

        Post post = optionalPost.get();

        if (post.getMember() == optionalMember.get()) {

            log.info("id:{} 게시글 삭제", post.getId());

            postRepository.deleteById(postId);
        } else {
            throw new RuntimeException("권한 없음");
        }

    }

}
