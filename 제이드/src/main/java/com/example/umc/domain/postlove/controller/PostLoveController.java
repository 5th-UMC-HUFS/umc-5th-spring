package com.example.umc.domain.postlove.controller;

import com.example.umc.domain.postlove.dto.PostLoveRequestDto;
import com.example.umc.domain.postlove.service.PostLoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostLoveController {
    private final PostLoveService postLoveService;

    @PostMapping("/post/{postId}/love")
    public ResponseEntity clickLove(@PathVariable Long postId, @RequestBody PostLoveRequestDto requestDto) {
        postLoveService.toggleLove(postId, requestDto);
        String msg = "좋아요를 취소했습니다.";
        if(postLoveService.isLoved(postId, requestDto)) {
            msg = "좋아요를 눌렀습니다";
        }
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
