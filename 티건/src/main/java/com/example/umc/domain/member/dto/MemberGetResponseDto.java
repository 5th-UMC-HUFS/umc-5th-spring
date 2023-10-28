package com.example.umc.domain.member.dto;

import com.example.umc.domain.member.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberGetResponseDto {

    public Long id;

    public String name;

    public String nickName;

    public String phoneNumber;

    public String birthDay;

    // entity to dto
    // static : 메모리 공유
    public static MemberGetResponseDto of (Member member){
        return MemberGetResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .nickName(member.getNickName())
                .phoneNumber(member.getPhoneNumber())
                .birthDay(member.getBirthDay())
                .build();
    }
}
