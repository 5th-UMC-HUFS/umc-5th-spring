package com.example.umc1.domain.post.service;

import com.example.umc1.domain.member.entity.Member;
import com.example.umc1.domain.member.repository.MemberRepository;
import com.example.umc1.domain.post.dto.PageDto;
import com.example.umc1.domain.post.dto.PostRequestDto;
import com.example.umc1.domain.post.dto.PostResponseDto;
import com.example.umc1.domain.post.dto.PostUpdateRequestDto;
import com.example.umc1.domain.post.entity.Post;
import com.example.umc1.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void createPost(PostRequestDto requestDto) {
        Member author = memberRepository.findById(requestDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다"));
        Post post = Post.builder()
                .author(author)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
        postRepository.save(post);
    }

    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 불러올 수 없습니다"));
        return PostResponseDto.of(post);
    }

    public List<PostResponseDto> getPostsByAuthor(Long authorId) {
        Member author = memberRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다"));
        List<Post> posts = postRepository.findByAuthor(author);
        List<PostResponseDto> dtos =posts.stream()
                .map(PostResponseDto::of)
                .collect(Collectors.toList());
        return dtos;
    }

    public PageDto getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Post> postPage = postRepository.findAll(pageable);
        boolean hasNext = postPage.hasNext();
        List<PostResponseDto> posts = postPage.getContent().stream()
                .map(PostResponseDto::of)
                .collect(Collectors.toList());
        return PageDto.builder()
                .posts(posts)
                .hasNext(hasNext)
                .build();
    }

    public void updatePost(Long postId, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 정보를 찾을 수 없습니다."));
        post.updatePost(requestDto);
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 정보를 찾을 수 없습니다."));
        postRepository.delete(post);
    }

}
