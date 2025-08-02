package org.sopt.comment.domain.exception;

import org.sopt.global.error.BusinessException;
import org.sopt.global.error.ErrorCode;

public class CommentLikeException extends BusinessException {

    public CommentLikeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
