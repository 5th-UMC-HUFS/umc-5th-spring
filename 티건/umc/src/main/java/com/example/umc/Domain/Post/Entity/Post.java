package com.example.umc.domain.post.entity;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.post.dto.PostUpdateRequestDto;
import com.example.umc.domain.postlike.entity.PostLike;
import com.example.umc.global.entity.CreateUpdateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends CreateUpdateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    // 회원과 맵핑
    // 원래는 member를 게시글을 생성한 member로 등록해야 하는데 service에서 등록하므로 pass
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLike> postLikeList = new ArrayList<>();

    public void update(PostUpdateRequestDto postUpdateRequestDto) {
        if (hasText(postUpdateRequestDto.getTitle())) {
            this.title = postUpdateRequestDto.getTitle();
        }
        if (hasText(postUpdateRequestDto.getContent())) {
            this.content = postUpdateRequestDto.getContent();
        }
    }

    public void like(PostLike postLike) {
        this.postLikeList.add(postLike);
    }

    public void disLike(PostLike postLike) {
        this.postLikeList.remove(postLike);
    }

}
