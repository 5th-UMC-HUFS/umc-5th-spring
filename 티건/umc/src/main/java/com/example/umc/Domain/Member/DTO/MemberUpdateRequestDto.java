package com.example.umc.Domain.Member.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateRequestDto {
    public String name;

    public String nickName;

    public String phoneNumber;
}
