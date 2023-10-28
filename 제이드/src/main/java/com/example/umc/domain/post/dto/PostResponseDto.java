package com.example.umc.domain.post.dto;

import com.example.umc.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {
    private Long postId;
    private String authorName;
    private String title;
    private String content;

    public static PostResponseDto of(Post post) {
        return PostResponseDto.builder()
                .postId(post.getPostId())
                .authorName(post.getAuthor().getName())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}