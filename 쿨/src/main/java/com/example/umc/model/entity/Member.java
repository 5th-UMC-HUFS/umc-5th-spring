package com.example.umc.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String nickname;

    private String phonenumber;

    private String birthday;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String name, String nickname, String phonenumber, String birthday){
        this.name = name;
        this.nickname = nickname;
        this.phonenumber = phonenumber;
        this.birthday = birthday;
    }

}
