package com.example.umcproject.likes.entity;

import com.example.umcproject.member.entity.Member;
import com.example.umcproject.post.entity.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
public class Likes {


    @Id  @GeneratedValue
    @Column(name = "LIKES_ID")
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member liker;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post targetPost ;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt ;


    // == 연관관계 메서드 == //
    /*
    Member 좋아요 리스트에 add
     */
    private void setLiker(Member member) {
        this.liker = member;
        member.getLikes().add(this) ;
    }

    /*
    Post 좋아요 리스트에 add
     */
    private void setPost(Post post) {
        this.targetPost = post;
        post.getLikes().add(this) ;
    }

    // == 비즈니스 로직 ==//
    /*
    좋아요 (생성 메서드)
     */
    public static Likes createLikes(Member member, Post post, LocalDateTime createdAt){

        Likes likes = new Likes();

        likes.setLiker(member);
        likes.setPost(post);
        likes.setCreatedAt(createdAt);

        return likes ;
    }

    /*
  좋아요 취소
   */
    public void cancelLike(){
        getLiker().getLikes().remove(this) ;
        getTargetPost().getLikes().remove(this) ;
    }

}
