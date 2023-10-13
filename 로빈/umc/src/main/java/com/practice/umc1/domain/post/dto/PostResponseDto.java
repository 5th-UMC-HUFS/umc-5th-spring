package com.practice.umc1.domain.post.dto;

import com.practice.umc1.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private String author;

    public static PostResponseDto of (Post post) {
        return PostResponseDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getMember().getName())
                .build();
    }
}
