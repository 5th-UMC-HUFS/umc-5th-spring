package com.example.umcproject.member.entity;


import com.example.umcproject.likes.entity.Likes;
import com.example.umcproject.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// @Repository // @repository는 이게 repository다 라는 역할을 지정해주는 것
// @Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name ="MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    private String name ;

    private String phoneNumber ;

    @Column(nullable = true)
    private LocalDateTime birthDay ;

    private MemberStatus memberStatus ;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "liker")
    private List<Likes> likes = new ArrayList<>();


    // 생성 메서드 //
    public static Member createMember( String name, String phoneNumber, LocalDateTime birthDay){

        Member member = new Member() ;

        member.name = name ;
        member.phoneNumber = phoneNumber ;
        member.birthDay = birthDay ;

        member.memberStatus = MemberStatus.ACTIVE ;

        return member ;

    }  // createMember

    // 비즈니스 로직 //
    public Member updateMember(Member member , String name, String phoneNumber, LocalDateTime birthDay) {

        member.name = name ;
        member.phoneNumber = phoneNumber;
        member.birthDay = birthDay ;

        return member ;
    }

    public void updateStatus(Member member , MemberStatus memberStatus){
        member.memberStatus = memberStatus ;

    }


} //Member
