package com.example.umcproject.likes.dto;

import com.example.umcproject.member.entity.Member;
import com.example.umcproject.post.entity.Post;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LikesRequestDTO {

    private Long memberId;

    private Long postId ;

    private LocalDateTime createdAt ;
}
