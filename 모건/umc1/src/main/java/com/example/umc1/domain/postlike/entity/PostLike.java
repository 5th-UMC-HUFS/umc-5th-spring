package com.example.umc1.domain.postlike.entity;

import com.example.umc1.domain.member.entity.Member;
import com.example.umc1.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"MEMBER_ID", "POST_ID"}))
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post targetPost;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setTargetPost(Post targetPost) {
        this.targetPost = targetPost;
    }
}
