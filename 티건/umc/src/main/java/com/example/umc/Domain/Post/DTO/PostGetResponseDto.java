package com.example.umc.Domain.Post.DTO;

import com.example.umc.Domain.Post.Entity.Post;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostGetResponseDto {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Long memberId;

    public static PostGetResponseDto of(Post post) {
        return PostGetResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .memberId(post.getMemberId())
                .build();
    }
}
