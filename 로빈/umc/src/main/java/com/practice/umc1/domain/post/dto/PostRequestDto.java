package com.practice.umc1.domain.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;
    private Long memberId;
}
