package com.practice.umc1.domain.post.service;

import com.practice.umc1.domain.global.exception.CustomException;
import com.practice.umc1.domain.global.exception.ExceptionEnum;
import com.practice.umc1.domain.member.entity.Member;
import com.practice.umc1.domain.post.dto.PostRequestDto;
import com.practice.umc1.domain.post.dto.PostResponseDto;
import com.practice.umc1.domain.post.entity.Post;
import com.practice.umc1.domain.post.repository.PostRepository;
//import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public ResponseEntity<PostResponseDto> createPost(PostRequestDto postRequestDto, Member member){
        Post post = new Post(postRequestDto, member);
        postRepository.save(post);

        return ResponseEntity.ok().body(new PostResponseDto(post));
    }

    @Transactional(readOnly = true)
    public ResponseEntity findPost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()) {
            throw  new CustomException(ExceptionEnum.NOT_EXIST_POST);
        }

        return ResponseEntity.ok().body(new PostResponseDto(post.get()));
    }

    @Transactional
    public ResponseEntity updatePost(Long id, PostRequestDto postRequestDto, Member member) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()) {
            throw  new CustomException(ExceptionEnum.NOT_EXIST_POST);
        }
        if (post.get().getMember() != member) {
            throw new CustomException(ExceptionEnum.INVALID_PERMISSION_TO_MODIFY);
        }

        post.get().update(postRequestDto);
        return ResponseEntity.ok().body(new PostResponseDto(post.get()));
    }

    @Transactional
    public ResponseEntity deletePost(Long id, Member member) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()) {
            throw new CustomException(ExceptionEnum.NOT_EXIST_POST);
        }
        if (post.get().getMember() != member) {
            throw new CustomException(ExceptionEnum.INVALID_PERMISSION_TO_DELETE);
        }

        postRepository.deleteById(id);
        return ResponseEntity.ok().body(new PostResponseDto(post.get()));
    }

}
