package com.example.umc.domain.post.controller;

import com.example.umc.domain.post.dto.PostDeleteRequestDto;
import com.example.umc.domain.post.dto.PostGetResponseDto;
import com.example.umc.domain.post.dto.PostRegisterRequestDto;
import com.example.umc.domain.post.dto.PostUpdateRequestDto;
import com.example.umc.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post/register")
    public ResponseEntity<String> registerPost(@RequestBody PostRegisterRequestDto postRegisterRequestDto){
        postService.registerPost(postRegisterRequestDto);
        return ResponseEntity.ok()
                .body("게시글 등록 성공");
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostGetResponseDto> getPost(@PathVariable Long postId){
        return ResponseEntity.ok()
                .body(postService.getPost(postId));
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostGetResponseDto>> getPosts(@RequestParam("id") Long id){
        return ResponseEntity.ok()
                .body(postService.getPosts(id));
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId,
                                             @RequestBody PostUpdateRequestDto postUpdateRequestDto){
        postService.updatePost(postId, postUpdateRequestDto);
        return ResponseEntity.ok()
                .body("게시글 수정 성공");
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId,
                                             @RequestBody PostDeleteRequestDto postDeleteRequestDto){
        postService.deletePost(postId, postDeleteRequestDto);
        return ResponseEntity.ok()
                .body("게시글 삭제 성공");
    }


}
