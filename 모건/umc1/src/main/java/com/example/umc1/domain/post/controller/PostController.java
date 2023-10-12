package com.example.umc1.domain.post.controller;

import com.example.umc1.domain.post.dto.PostRequestDto;
import com.example.umc1.domain.post.dto.PostUpdateRequestDto;
import com.example.umc1.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping()
    public ResponseEntity createPost(
            @RequestBody PostRequestDto requestDto
    ) {
        postService.createPost(requestDto);
        return new ResponseEntity<>("게시글 작성 완료", HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity getPost(
            @PathVariable Long postId
    ) {
        return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity getPostByAuthor(
            @PathVariable Long authorId
    ) {
        return new ResponseEntity<>(postService.getPostsByAuthor(authorId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getAllPosts(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return new ResponseEntity<>(postService.getAllPosts(page - 1, size), HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequestDto requestDto
    ) {
        postService.updatePost(postId, requestDto);
        return new ResponseEntity<>("게시글 수정 완료", HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(
            @PathVariable Long postId
    ) {
        postService.deletePost(postId);
        return new ResponseEntity<>("게시글 삭제 완료", HttpStatus.OK);
    }
}
