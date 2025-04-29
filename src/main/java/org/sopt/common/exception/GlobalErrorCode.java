package org.sopt.common.exception;

import org.springframework.http.HttpStatus;

public enum GlobalErrorCode implements ErrorCode {

    // 400 Bad Request
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "⚠️ 요청 경로의 파라미터는 올바른 형식이 아닙니다."),

    // 404 Not Found
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "⚠️ 존재하지 않는 리소스입니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "😱 500이 무서버~");

    private final HttpStatus status;
    private final String message;

    GlobalErrorCode(HttpStatus status, String message) {
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
