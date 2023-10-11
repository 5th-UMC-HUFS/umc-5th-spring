package com.example.umcproject.post.entity;

import com.example.umcproject.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id  @GeneratedValue
    @Column(name = "POST_ID")
    private Long id ;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member ;


    private String title ;

    private String content ;


    // 생성 메서드 //
    public Post createPost(Member member, String title, String conten){

        Post post = new Post();
        this.member = member ;
        this.title = title ;
        this.content = content ;

        return post ;

    }  // createMember

}
