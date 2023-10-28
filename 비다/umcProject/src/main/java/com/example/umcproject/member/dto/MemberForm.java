package com.example.umcproject.member.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name ;

    private String phoneNumber ;

    private LocalDateTime birthDay ;

}
