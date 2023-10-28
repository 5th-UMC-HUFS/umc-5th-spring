package com.example.umc.domain.member.dto;

import com.example.umc.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDto {
    private Long memberId;
    private String name;
    private String nickname;
    private String birthday;
    private String phoneNumber;

    //builder로 해도 되고 setter로 해도되고 원하는 대로 해줘용
    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .nickname(member.getNickname())
                .birthday(member.getBirthday())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}