package org.sopt.comment.application.exception;

import lombok.RequiredArgsConstructor;
import org.sopt.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {

    // 400
    INVALID_CONTENT_BLANK(HttpStatus.BAD_REQUEST, "댓글은 비워둘 수 없습니다."),
    INVALID_CONTENT_LENGTH(HttpStatus.BAD_REQUEST, "댓글 길이는 300자를 넘을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
