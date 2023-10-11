package com.example.umc.domain.postlike.dto;

import com.example.umc.domain.postlike.entity.PostLike;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeResponseDto {

    private Long id;

    private Long memberId;

    private Long postId;

    public static PostLikeResponseDto of (PostLike postLike){
        return PostLikeResponseDto.builder()
                .id(postLike.getId())
                .memberId(postLike.getMember().getId())
                .postId(postLike.getPost().getId())
                .build();
    }
}
