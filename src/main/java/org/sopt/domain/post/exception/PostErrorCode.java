package org.sopt.domain.post.exception;

import org.sopt.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

public enum PostErrorCode implements ErrorCode {

    // 400 BAD REQUEST
    INVALID_TITLE_BLANK(HttpStatus.BAD_REQUEST, "⚠️ 게시글 제목은 비워둘 수 없습니다!"),
    INVALID_TITLE_LENGTH(HttpStatus.BAD_REQUEST, "⚠️ 게시글 제목은 30자를 넘을 수 없습니다!"),
    INVALID_CONTENT_BLANK(HttpStatus.BAD_REQUEST, "⚠️ 게시글 내용은 비워둘 수 없습니다!"),
    INVALID_CONTENT_LENGTH(HttpStatus.BAD_REQUEST, "⚠️ 게시글 제목은 1,000자를 넘을 수 없습니다!"),
    INVALID_CREATE_TIME(HttpStatus.BAD_REQUEST, "⚠️ 3분이 지나야 새 글을 작성할 수 있습니다."),
    INVALID_SEARCH_TYPE(HttpStatus.BAD_REQUEST, "⚠️ 검색 타입이 잘못되었습니다."),
    INVALID_TAG_TYPE(HttpStatus.BAD_REQUEST, "⚠️ 존재하지 않는 타입입니다."),

    // 401 Unauthorized
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "⚠️ 유저 인증이 필요합니다."),

    // 403 Forbidden
    POST_DELETE_UNAUTHORIZED(HttpStatus.FORBIDDEN, "⚠️ 해당 게시글을 삭제할 권한이 없습니다."),
    POST_UPDATE_UNAUTHORIZED(HttpStatus.FORBIDDEN, "⚠️ 해당 게시글을 수정할 권한이 없습니다."),

    // 404 NOT FOUND
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "⚠️ 해당 게시글을 찾을 수 없습니다!"),

    // 409 CONFLICT
    TITLE_DUPLICATED(HttpStatus.CONFLICT, "⚠️ 게시글 제목은 중복될 수 없습니다!");

    private final HttpStatus status;
    private final String message;

    PostErrorCode(HttpStatus status, String message) {
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
