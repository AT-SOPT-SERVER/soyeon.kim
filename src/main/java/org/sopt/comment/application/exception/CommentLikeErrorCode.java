package org.sopt.comment.application.exception;

import lombok.RequiredArgsConstructor;
import org.sopt.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum CommentLikeErrorCode implements ErrorCode {

    // 409 Conflict
    COMMENT_ALREADY_LIKED(HttpStatus.CONFLICT, "이미 이 댓글에 좋아요를 눌렀습니다.");

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
