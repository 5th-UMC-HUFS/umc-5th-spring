package com.practice.umc1.domain.global.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MsgResponseDto {

    private int status;


    private String message;

    @Builder
    private MsgResponseDto(int status, String message){
        this.status = status;
        this.message = message;
    }

    public static MsgResponseDto of(int status, String message){
        return MsgResponseDto.builder()
                .status(status)
                .message(message)
                .build();
    }

}