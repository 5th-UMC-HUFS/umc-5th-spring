package com.example.umc.controller;

import com.example.umc.entity.Post;
import com.example.umc.entity.PostRequest;
import com.example.umc.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/post")
@RestController
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest){
        Post post = this.postService.createPost(postRequest);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<Post> getPost(@PathVariable(name = "Id") Long Id){
        Post post = this.postService.getPost(Id);
        return ResponseEntity.ok().body(post);
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<Post> updatePost(@PathVariable(name = "Id") Long Id, @RequestBody PostRequest postRequest){
        Post post = this.postService.updatePost(postRequest, Id);
        return ResponseEntity.ok().body(post);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<Post> deletePost(@PathVariable(name = "Id") Long Id){
        Post post = this.postService.deletePost(Id);
        return ResponseEntity.ok().body(post);
    }

}
