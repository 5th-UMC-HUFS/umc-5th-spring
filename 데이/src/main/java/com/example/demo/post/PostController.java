package com.example.demo.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostEntity> createPost(@RequestBody PostDto dto) {
        return new ResponseEntity<>(postService.createPost(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> deletePost(@PathVariable String title) {
        postService.deletePost(title);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/title/{title}")
    public ResponseEntity<List<PostDto>> putPostsByTitle(@PathVariable String title, @RequestBody PostDto dto){
        return new ResponseEntity<>(postService.putPostsByTitle(title, dto), HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<PostDto>> getPostsByTitle(@PathVariable String title) {
        return new ResponseEntity<>(postService.getPostsByTitle(title), HttpStatus.OK);
    }

}