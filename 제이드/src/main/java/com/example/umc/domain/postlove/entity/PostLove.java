package com.example.umc.domain.postlove.entity;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostLove {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postLoveId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post targetPost;

}
