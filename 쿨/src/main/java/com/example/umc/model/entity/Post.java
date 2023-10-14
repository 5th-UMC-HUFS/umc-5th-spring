package com.example.umc.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_Id;

    private String title;

    private String content;

    private int post_like = 0;

    @ElementCollection
    private List<Long> who_likes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id")
    Member member;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
