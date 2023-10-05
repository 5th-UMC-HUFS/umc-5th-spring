package com.example.umc.Domain.Member.Entity;

import com.example.umc.Domain.Member.DTO.MemberUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static org.springframework.util.StringUtils.hasText;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long Id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NICKNAME")
    private String nickName;

    @Column(name = "PHONENUMBER")
    private String phoneNumber;

    @Column(name = "BIRTHDAY")
    private String birthDay;

    public void update(MemberUpdateRequestDto memberUpdateDto) {
        if (hasText(memberUpdateDto.getName())) {
            this.name = memberUpdateDto.getName();
        }
        if (hasText(memberUpdateDto.getPhoneNumber())) {
            this.phoneNumber = memberUpdateDto.getPhoneNumber();
        }
        if (hasText(memberUpdateDto.getNickName())) {
            this.nickName = memberUpdateDto.getNickName();
        }

    }
}
