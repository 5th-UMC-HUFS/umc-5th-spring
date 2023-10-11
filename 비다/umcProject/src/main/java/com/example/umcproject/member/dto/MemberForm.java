package com.example.umcproject.member.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name ;

    private String phoneNumber ;

    private LocalDateTime birthDay ;

}
