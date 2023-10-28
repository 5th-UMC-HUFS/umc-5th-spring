package com.example.umcproject.post.entity;

import com.example.umcproject.likes.entity.Likes;
import com.example.umcproject.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id  @GeneratedValue
    @Column(name = "POST_ID")
    private Long id ;

    @ManyToOne(fetch=FetchType.LAZY) // , cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID") // cascade는 member에만 있어야함
    private Member author ;

    private String title ;

    private String content ;

    @OneToMany(mappedBy = "targetPost")
    private List<Likes> likes  = new ArrayList<>() ;


    // 생성 메서드 //
    public static Post createPost(Member member, String title, String content){

        Post post = new Post();
        post.author = member ;
        post.title = title ;
        post.content = content ;

        return post ;

    }  // createMember




}
