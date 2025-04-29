package org.sopt.domain.user.exception;

import org.sopt.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

public enum UserErrorCode implements ErrorCode {

    // 400 Bad Request
    INVALID_NAME_BLANK(HttpStatus.BAD_REQUEST, "⚠️ 유저 이름은 비워둘 수 없습니다!");

    private final HttpStatus status;
    private final String message;

    UserErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
