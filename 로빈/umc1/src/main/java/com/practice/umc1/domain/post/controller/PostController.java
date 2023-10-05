package com.practice.umc1.domain.post.controller;


import com.practice.umc1.domain.member.entity.Member;
import com.practice.umc1.domain.post.dto.PostRequestDto;
import com.practice.umc1.domain.post.dto.PostResponseDto;
import com.practice.umc1.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping()
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, Member member ) {
        return postService.createPost(postRequestDto, member);
    }

    @GetMapping("/{postId}")
    public ResponseEntity findPost(@PathVariable Long id) {
        return postService.findPost(id);
    }

    @PutMapping("/{postId}")
    public ResponseEntity updateMember(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, Member member) {
        return postService.updatePost(id, postRequestDto, member);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(@PathVariable Long id, Member member) {
        return postService.deletePost(id, member);
    }

}