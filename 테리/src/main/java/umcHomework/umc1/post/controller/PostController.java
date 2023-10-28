package umcHomework.umc1.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umcHomework.umc1.post.dto.PostRequestDto;
import umcHomework.umc1.post.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping()
    public ResponseEntity createPost(
            @RequestBody PostRequestDto postRequestDto
    ){
        postService.createPost(postRequestDto);
        return new ResponseEntity<>("게시글 작성 완료", HttpStatus.CREATED);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity getPost(
            @PathVariable Long memberId
    ){
        return new ResponseEntity<>(postService.getPost(memberId), HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto postRequestDto
    ){
        postService.updatePost(postRequestDto, postId);
        return new ResponseEntity<>("게시글 수정 성공", HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(
            @PathVariable Long postId
    ){
        postService.deletePost(postId);
        return new ResponseEntity<>("게시글 삭제 성공", HttpStatus.OK);
    }
}
