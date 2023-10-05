package com.practice.umc1.domain.member.dto;

import com.practice.umc1.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private String name;
    private String nickname;
    private int phoneNum;
    private String birthday;

    @Builder
    public MemberResponseDto(Member member) {
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.phoneNum = member.getPhoneNum();
        this.birthday = member.getBirthday();
    }


}
