package org.sopt.user.domain.exception;

import org.sopt.global.error.BusinessException;
import org.sopt.global.error.ErrorCode;

public class UserException extends BusinessException {

    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
