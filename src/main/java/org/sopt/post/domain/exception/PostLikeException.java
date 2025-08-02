package org.sopt.post.domain.exception;

import org.sopt.global.error.BusinessException;
import org.sopt.global.error.ErrorCode;

public class PostLikeException extends BusinessException {

    public PostLikeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
