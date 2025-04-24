package org.sopt.common.exception;

import org.springframework.http.HttpStatus;

public enum GlobalErrorCode implements ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500이 무서버~");

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
