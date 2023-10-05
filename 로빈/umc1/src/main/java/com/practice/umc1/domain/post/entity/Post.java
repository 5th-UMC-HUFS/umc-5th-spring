package com.practice.umc1.domain.post.entity;

import com.practice.umc1.domain.member.entity.Member;
import com.practice.umc1.domain.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Post(PostRequestDto postRequestDto, Member member) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.member = member;
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }
}
