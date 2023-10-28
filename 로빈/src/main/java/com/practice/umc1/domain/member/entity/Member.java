package com.practice.umc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String nickname;

    @Column
    private int phoneNum;

    @Column
    private String email;

    @Column
    private String birthday;

    public Member(String name, String nickname, int phoneNum, String email, String birthday) {
        this.name = name;
        this.nickname = nickname;
        this.phoneNum = phoneNum;
        this.email = email;
        this.birthday = birthday;
    }

//    @Builder
//    public Member(String name, String nickname, int phoneNum, String birthday) {
//        this.name = name;
//        this.nickname = nickname;
//        this.phoneNum = phoneNum;
//        this.birthday = birthday;
//    }
}
