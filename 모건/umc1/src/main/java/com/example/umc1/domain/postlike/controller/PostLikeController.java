package com.example.umc1.domain.postlike.controller;

import com.example.umc1.domain.postlike.dto.PostLIkeRequestDto;
import com.example.umc1.domain.postlike.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;

    @PostMapping("/post/{postId}/like")
    public ResponseEntity pressLike(
            @PathVariable Long postId,
            @RequestBody PostLIkeRequestDto requestDto
    ) {
        postLikeService.likePost(postId, requestDto);
        String message = null;
        if (postLikeService.isLiked(postId,requestDto)){
            message = "좋아요를 눌렀습니다.";
        } else {
            message = "좋아요를 취소했습니다.";
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/member/{memberId}/post_likes")
    public ResponseEntity getPostLikeByMember(
            @PathVariable Long memberId
    ) {
        return new ResponseEntity<>(postLikeService.getLikesByMember(memberId), HttpStatus.OK);
    }

    @GetMapping("/post/{postId}/likes")
    public ResponseEntity getPostLikeByPost(
            @PathVariable Long postId
    ) {
        return new ResponseEntity<>(postLikeService.getLikesByPost(postId), HttpStatus.OK);
    }
}
