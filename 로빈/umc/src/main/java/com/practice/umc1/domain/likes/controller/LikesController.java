package com.practice.umc1.domain.likes.controller;

import com.practice.umc1.domain.likes.dto.LikesRequestDto;
import com.practice.umc1.domain.likes.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {

    private final LikesService likesService;

    @PutMapping("/post/{postId}")
    public ResponseEntity LikesPost(@PathVariable Long postId, LikesRequestDto likesRequestDto){
        return likesService.likesPost(postId, likesRequestDto);
    }

}
