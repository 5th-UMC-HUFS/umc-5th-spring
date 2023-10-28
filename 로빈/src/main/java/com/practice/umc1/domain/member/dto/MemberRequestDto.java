package com.practice.umc1.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {

    private String name;
    private String nickname;
    private int phoneNum;
    private String email;
    private String birthday;

}
