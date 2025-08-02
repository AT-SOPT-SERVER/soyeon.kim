package org.sopt.comment.domain.exception;

import lombok.RequiredArgsConstructor;
import org.sopt.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum CommentLikeErrorCode implements ErrorCode {

    // 404 Not Found
    COMMENT_LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 댓글에 좋아요를 누르지 않았습니다."),

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
