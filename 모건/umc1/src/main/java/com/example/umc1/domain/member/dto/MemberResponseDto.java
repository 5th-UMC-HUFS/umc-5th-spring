package com.example.umc1.domain.member.dto;

import com.example.umc1.domain.member.entity.Member;
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
