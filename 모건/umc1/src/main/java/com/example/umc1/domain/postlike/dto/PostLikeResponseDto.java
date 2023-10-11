package com.example.umc1.domain.postlike.dto;

import com.example.umc1.domain.postlike.entity.PostLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikeResponseDto {
    private Long postLikeId;
    private Long memberId;
    private Long postId;
    private String title;
    private String content;

    public static PostLikeResponseDto of(PostLike postLike) {
        return PostLikeResponseDto.builder()
                .postLikeId(postLike.getId())
                .memberId(postLike.getMember().getMemberId())
                .postId(postLike.getTargetPost().getPostId())
                .title(postLike.getTargetPost().getTitle())
                .content(postLike.getTargetPost().getContent())
                .build();
    }
}
