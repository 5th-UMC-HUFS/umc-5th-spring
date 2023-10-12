package com.example.umc.domain.postlike.controller;

import com.example.umc.domain.postlike.dto.PostLikeRequestDto;
import com.example.umc.domain.postlike.dto.PostLikeResponseDto;
import com.example.umc.domain.postlike.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/post/{postId}/like")
    public ResponseEntity<String> postLike(@PathVariable Long postId,
                                           @RequestBody PostLikeRequestDto postLikeRequestDto){
        if(postLikeService.postLike(postId, postLikeRequestDto)){
            return ResponseEntity.ok()
                    .body("좋아요 성공");
        } else {
            return ResponseEntity.ok()
                    .body("좋아요 취소");
        }
    }

    @GetMapping("/member/{memberId}/like")
    public ResponseEntity<List<PostLikeResponseDto>> memberLike(@PathVariable Long memberId){
        return ResponseEntity.ok()
                .body(postLikeService.getPostsLikeByMemberId(memberId));
    }

    @GetMapping("/post/{postId}/like")
    public ResponseEntity<List<PostLikeResponseDto>> postLike(@PathVariable Long postId){
        return ResponseEntity.ok()
                .body(postLikeService.getPostsLikeByPostId(postId));
    }
}
