package com.example.umc.domain.post.dto;

import com.example.umc.domain.post.entity.Post;
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

    private String memberNickName;

    public static PostGetResponseDto of(Post post) {
        return PostGetResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .memberId(post.getMember().getId())
                .memberNickName(post.getMember().getNickName())
                .build();
    }
}
