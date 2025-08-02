package org.sopt.comment.domain.exception;

import org.sopt.global.error.BusinessException;
import org.sopt.global.error.ErrorCode;

public class CommentException extends BusinessException {

    public CommentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
