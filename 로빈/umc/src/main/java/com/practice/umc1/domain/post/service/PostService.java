package com.practice.umc1.domain.post.service;

import com.practice.umc1.domain.member.repository.MemberRepository;
import com.practice.umc1.domain.post.dto.PostResponseDto;
import com.practice.umc1.global.dto.MessageResponseDto;
import com.practice.umc1.global.exception.CustomException;
import com.practice.umc1.global.exception.ExceptionEnum;
import com.practice.umc1.domain.member.entity.Member;
import com.practice.umc1.domain.post.dto.PostRequestDto;
import com.practice.umc1.domain.post.entity.Post;
import com.practice.umc1.domain.post.repository.PostRepository;
//import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ResponseEntity<MessageResponseDto> createPost(PostRequestDto postRequestDto){
        Optional<Member> member = memberRepository.findById(postRequestDto.getMemberId());
        if(member.isEmpty()) {
            throw new CustomException(ExceptionEnum.NOT_EXIST_MEMBER);
        }

        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .member(member.get())
                .build();

        postRepository.save(post);

        return ResponseEntity.ok().body(MessageResponseDto.of(HttpStatus.OK.value(), "게시글 등록 완료"));
    }

    @Transactional(readOnly = true)
    public ResponseEntity findPost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()) {
            throw new CustomException(ExceptionEnum.NOT_EXIST_POST);
        }

        return ResponseEntity.ok().body(PostResponseDto.of(post.get()));
    }

    @Transactional
    public ResponseEntity updatePost(Long id, PostRequestDto postRequestDto) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()) {
            throw  new CustomException(ExceptionEnum.NOT_EXIST_POST);
        }
        if (post.get().getMember().getId() != postRequestDto.getMemberId()) {
            throw new CustomException(ExceptionEnum.INVALID_PERMISSION_TO_MODIFY);
        }

        post.get().update(postRequestDto);
        return ResponseEntity.ok().body(MessageResponseDto.of(HttpStatus.OK.value(), "게시글 수정 완료"));
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
        return ResponseEntity.ok().body(MessageResponseDto.of(HttpStatus.OK.value(), "게시글 삭제 완료"));
    }

}
