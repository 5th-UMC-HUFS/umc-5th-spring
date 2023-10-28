package com.example.umc.domain.member.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRegisterRequestDto {

    public String name;

    public String nickName;

    public String phoneNumber;

    public String birthDay;
}
