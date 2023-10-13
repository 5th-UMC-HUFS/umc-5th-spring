package com.example.umc.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000 : 오류
     */
    // Common
    INVALID_JWT(false, 2000, "유효하지 않은 JWT입니다."),
    NO_MEMBER(false,2001,"맴버가 없습니다."),
    NO_POST(false,2002,"게시글이 없습니다."),
    WRONG_ID(false,2003,"ID가 잘못되었습니다.");



    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
