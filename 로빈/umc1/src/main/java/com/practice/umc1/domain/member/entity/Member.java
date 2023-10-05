package com.practice.umc1.domain.member.entity;

import com.practice.umc1.domain.member.dto.MemberRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String nickname;

    @Column
    private int phoneNum;

    @Column
    private String birthday;


    @Builder
    public Member(MemberRequestDto memberRequestDto, Member member) {
        this.name = memberRequestDto.getName();
        this.nickname = memberRequestDto.getNickname();
        this.phoneNum = memberRequestDto.getPhoneNum();
        this.birthday = memberRequestDto.getBirthday();
    }

    public void update(MemberRequestDto memberRequestDto) {
        this.name = memberRequestDto.getName();
        this.nickname = memberRequestDto.getNickname();
        this.phoneNum = memberRequestDto.getPhoneNum();
        this.birthday = memberRequestDto.getBirthday();
    }
}
