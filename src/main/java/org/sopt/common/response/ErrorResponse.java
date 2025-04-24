package org.sopt.common.response;

import org.sopt.common.exception.ErrorCode;

public class ErrorResponse {

    private final int status;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus().value();
        this.message = errorCode.getMessage();
    }
}
