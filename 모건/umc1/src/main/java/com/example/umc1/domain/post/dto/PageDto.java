package com.example.umc1.domain.post.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDto {
    private List<PostResponseDto> posts;
    private boolean hasNext;
}
