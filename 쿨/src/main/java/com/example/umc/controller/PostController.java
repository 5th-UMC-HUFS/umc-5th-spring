package com.example.umc.controller;

import com.example.umc.config.BaseException;
import com.example.umc.config.BaseResponse;
import com.example.umc.model.entity.Post;
import com.example.umc.model.dto.PostRequest;
import com.example.umc.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {this.postService = postService;}

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest){
        Post post = postService.createPost(postRequest);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/get/{Id}")
    public BaseResponse<Post> getPost(@PathVariable(name = "Id") Long Id){
        try{
            Post post = postService.getPost(Id);
            return new BaseResponse<>(post);
        } catch(BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @PatchMapping("/update/{Id}")
    public BaseResponse<Post> updatePost(@PathVariable(name = "Id") Long Id, @RequestBody PostRequest postRequest){
        try{
            Post post = postService.updatePost(postRequest, Id);
            return new BaseResponse<>(post);
        } catch(BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @DeleteMapping("/delete/{Id}")
    public BaseResponse<String> deletePost(@PathVariable(name = "Id") Long Id){
        try{
            postService.deletePost(Id);
            String result = "삭제되었습니다.";
            return new BaseResponse<>(result);
        } catch(BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @PatchMapping("like/{pId}")
    public BaseResponse<String> likePost(@RequestParam Long Id,
                                         @PathVariable(name = "pId") Long pId){
        try{
            postService.likePost(Id, pId);
            String result = "Like";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
