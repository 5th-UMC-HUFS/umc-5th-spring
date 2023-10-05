package com.example.umc.Domain.Post.Controller;

import com.example.umc.Domain.Post.DTO.PostGetResponseDto;
import com.example.umc.Domain.Post.DTO.PostRegisterRequestDto;
import com.example.umc.Domain.Post.DTO.PostUpdateRequestDto;
import com.example.umc.Domain.Post.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post/register/{memberId}")
    public ResponseEntity<String> registerPost(@PathVariable Long memberId,
                                               @RequestBody PostRegisterRequestDto postRegisterRequestDto){
        postService.registerPost(memberId, postRegisterRequestDto);
        return ResponseEntity.ok()
                .body("게시글 등록 성공");
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostGetResponseDto> getPost(@PathVariable Long postId){
        return ResponseEntity.ok()
                .body(postService.getPost(postId));
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostGetResponseDto>> getPosts(@RequestParam("memberid") Long memberId){
        return ResponseEntity.ok()
                .body(postService.getPosts(memberId));
    }

    @PutMapping("/post/{postId}/{memberId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId,
                                             @PathVariable Long memberId,
                                             @RequestBody PostUpdateRequestDto postUpdateRequestDto){
        postService.updatePost(memberId, postId, postUpdateRequestDto);
        return ResponseEntity.ok()
                .body("게시글 수정 성공");
    }

    @DeleteMapping("/post/{postId}/{memberId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId,
                                             @PathVariable Long memberId){
        postService.deletePost(memberId, postId);
        return ResponseEntity.ok()
                .body("게시글 삭제 성공");
    }
}
