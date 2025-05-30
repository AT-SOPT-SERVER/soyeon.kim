package org.sopt.global.error;

import lombok.extern.slf4j.Slf4j;
import org.sopt.global.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResponse<Void> handleNotFound(NoHandlerFoundException ex) {
        log.warn("NoHandlerFoundException 발생: {}", ex.getMessage());

        return ApiResponse.error(GlobalErrorCode.RESOURCE_NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Void> handleBusinessException(BusinessException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        return ApiResponse.error(errorCode);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponse<Void> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        log.warn("MethodArgumentTypeMismatchException 발생: {}", ex.getMessage());

        return ApiResponse.error(GlobalErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleUnhandledException(Exception ex) {
        log.warn("서버 내부 오류 발생: {}", ex.getMessage());

        return ApiResponse.error(GlobalErrorCode.INTERNAL_SERVER_ERROR);
    }
}
