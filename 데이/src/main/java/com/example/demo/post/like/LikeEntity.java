package com.example.demo.post.like;

import com.example.demo.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import com.example.demo.post.PostEntity;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "postId")
    private PostEntity post;

    public LikeEntity(UserEntity user, PostEntity post) {
        this.user = user;
        this.post = post;
    }
}

