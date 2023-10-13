package com.practice.umc1.global.exception;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    NOT_EXIST_MEMBER(404, "해당 사용자가 존재하지 않습니다."),
    NOT_EXIST_POST(404, "해당 게시글이 존재하지 않습니다."),
    INVALID_PERMISSION_TO_MODIFY(403, "작성자만 글을 수정할 수 있습니다."),
    INVALID_PERMISSION_TO_DELETE(403, "작성자만 글을 삭제할 수 있습니다.");

    private final int code;
    private final String msg;

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}