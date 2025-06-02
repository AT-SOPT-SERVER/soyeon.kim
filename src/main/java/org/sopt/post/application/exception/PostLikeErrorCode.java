package org.sopt.post.application.exception;

import lombok.RequiredArgsConstructor;
import org.sopt.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum PostLikeErrorCode implements ErrorCode {

    // 409 Conflict
    POST_ALREADY_LIKED(HttpStatus.CONFLICT, "이미 이 게시글에 좋아요를 눌렀습니다.");

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getStatus(){
        return this.status;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
