package org.sopt.post.domain.exception;

import org.sopt.global.error.BusinessException;
import org.sopt.global.error.ErrorCode;

public class PostException extends BusinessException {

    public PostException(ErrorCode errorCode) {
        super(errorCode);
    }
}
