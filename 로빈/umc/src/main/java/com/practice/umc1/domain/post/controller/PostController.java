package com.practice.umc1.domain.post.controller;


import com.practice.umc1.domain.member.entity.Member;
import com.practice.umc1.domain.post.dto.PostRequestDto;
import com.practice.umc1.domain.post.service.PostService;
import com.practice.umc1.global.dto.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping()
    public ResponseEntity<MessageResponseDto> createPost(@RequestBody PostRequestDto postRequestDto ) {
        return postService.createPost(postRequestDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity findPost(@PathVariable Long postId) {
        return postService.findPost(postId);
    }

    @PutMapping("/{postId}")
    public ResponseEntity updateMember(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(postId, postRequestDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId, Member member) {
        return postService.deletePost(postId, member);
    }

}