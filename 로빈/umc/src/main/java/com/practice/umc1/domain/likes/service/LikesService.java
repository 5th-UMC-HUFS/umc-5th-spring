package com.practice.umc1.domain.likes.service;

import com.practice.umc1.domain.likes.dto.LikesRequestDto;
import com.practice.umc1.domain.likes.entity.Likes;
import com.practice.umc1.domain.likes.repository.LikesRepository;
import com.practice.umc1.domain.member.entity.Member;
import com.practice.umc1.domain.member.repository.MemberRepository;
import com.practice.umc1.domain.post.entity.Post;
import com.practice.umc1.domain.post.repository.PostRepository;
import com.practice.umc1.global.dto.MessageResponseDto;
import com.practice.umc1.global.exception.CustomException;
import com.practice.umc1.global.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final PostRepository postRepository;
    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;

    public ResponseEntity likesPost(Long id, LikesRequestDto likesRequestDto ) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new CustomException(ExceptionEnum.NOT_EXIST_POST);
        }

        Optional<Member> member = memberRepository.findById(likesRequestDto.getMemberId());
        if(member.isEmpty()) {
            throw new CustomException(ExceptionEnum.NOT_EXIST_MEMBER);
        }

        Optional<Likes> likes = likesRepository.findByPost(post.get());
        if (likes.isEmpty()) {
            Likes newLikes = Likes.builder()
                    .post(post.get())
                    .member(member.get())
                    .build();
            likesRepository.save(newLikes);
            return ResponseEntity.ok().body(MessageResponseDto.of(HttpStatus.OK.value(), "좋아요 추가"));
        } else {
            likesRepository.delete(likes.get());
        }
        return ResponseEntity.ok().body(MessageResponseDto.of(HttpStatus.OK.value(), "좋아요 취소"));
    }
}
