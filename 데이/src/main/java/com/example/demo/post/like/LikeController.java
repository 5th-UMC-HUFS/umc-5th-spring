package com.example.demo.post.like;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/add")
    public ResponseEntity<Void> addLike(@RequestBody LikeDto likeDto) {
        likeService.addLike(likeDto.getUserId(), likeDto.getPostId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> removeLike(@RequestBody LikeDto likeDto) {
        likeService.removeLike(likeDto.getUserId(), likeDto.getPostId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
