package com.example.umc.Domain.Post.Service;

import com.example.umc.Domain.Member.Entity.Member;
import com.example.umc.Domain.Member.Repository.MemberRepository;
import com.example.umc.Domain.Post.DTO.PostGetResponseDto;
import com.example.umc.Domain.Post.DTO.PostRegisterRequestDto;
import com.example.umc.Domain.Post.DTO.PostUpdateRequestDto;
import com.example.umc.Domain.Post.Entity.Post;
import com.example.umc.Domain.Post.Repository.PostRepository;
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
    public void registerPost(Long memberId, PostRegisterRequestDto postRegisterRequestDto) {

        Optional<Member> optionalMember = memberRepository.findById(memberId);

        optionalMember
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

        Post post = Post.builder()
                .title(postRegisterRequestDto.getTitle())
                .content((postRegisterRequestDto.getContent()))
                .memberId(memberId)
                .build();
        postRepository.save(post);

    }

    @Transactional
    public PostGetResponseDto getPost(Long postId) {

        Optional<Post> optionalPost = postRepository.findById(postId);

        optionalPost
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));

        Post post = optionalPost.get();

        return PostGetResponseDto.of(post);
    }


    @Transactional
    public List<PostGetResponseDto> getPosts(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        optionalMember
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

        return postRepository.findByMemberId(memberId)
                .stream()
                .map(PostGetResponseDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updatePost(Long memberId, Long postId, PostUpdateRequestDto postUpdateRequestDto) {

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Optional<Post> optionalPost = postRepository.findById(postId);

        optionalMember
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

        optionalPost
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));

        Post post = optionalPost.get();

        if (post.getMemberId() == memberId) {
            post.update(postUpdateRequestDto);
        } else {
            throw new RuntimeException("권한 없음");
        }


    }

    @Transactional
    public void deletePost(Long memberId, Long postId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Optional<Post> optionalPost = postRepository.findById(postId);

        optionalMember
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

        optionalPost
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));

        Post post = optionalPost.get();

        if (post.getMemberId() == memberId) {
            postRepository.deleteById(postId);
        } else {
            throw new RuntimeException("권한 없음");
        }

    }

}
