package umcHomework.umc1.PostLikes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umcHomework.umc1.PostLikes.dto.PostLikesRequestDto;
import umcHomework.umc1.PostLikes.service.PostLikesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postLikes")
public class PostLikesController {
    private final PostLikesService postLikesService;
    private ResponseEntity<PostLikesRequestDto> postLikesRequestDtoResponseEntity;

    @PostMapping
    public ResponseEntity<PostLikesRequestDto> createPostLikes(
            @RequestBody PostLikesRequestDto postLikesRequestDto
    ){
        postLikesService.createPostLikes(postLikesRequestDto);
        return new ResponseEntity<>(postLikesRequestDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<PostLikesRequestDto> deletePostLikes(
            @RequestBody PostLikesRequestDto postLikesRequestDto
    ){
        postLikesService.deletePostLikes(postLikesRequestDto);
        return postLikesRequestDtoResponseEntity;
    }
}
