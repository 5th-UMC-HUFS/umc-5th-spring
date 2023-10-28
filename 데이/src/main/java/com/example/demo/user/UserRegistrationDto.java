package com.example.demo.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UserRegistrationDto {
    private Long userId;
    private String name;
    private String phoneNumber;
    private String birthDay;

    // 세터
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "userId : " + userId +
                ", name : '" + name + '\'' +
                ", phoneNumber : '" + phoneNumber + '\'' +
                ", birthDay : '" + birthDay + '\'' +
                '}';
    }
}


