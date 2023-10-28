package com.example.umc.domain.post.entity;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.post.dto.PostUpdateRequestDto;
import com.example.umc.domain.postlove.entity.PostLove;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member author;

    @Builder.Default
    @OneToMany(mappedBy = "targetPost", cascade = CascadeType.ALL)
    private List<PostLove> loveLists = new ArrayList<>();

    public void updatePost(PostUpdateRequestDto requestDto){
        if (requestDto.getTitle() != null) {
            this.title = requestDto.getTitle();
        }
        if (requestDto.getContent() != null) {
            this.content = requestDto.getContent();
        }
    }

    public void removeLove(PostLove postLove) {
        loveLists.remove(postLove);
        postLove.setTargetPost(null);
    }

    public void addLove(PostLove postLove) {
        loveLists.add(postLove);
        postLove.setTargetPost(this);
    }
}