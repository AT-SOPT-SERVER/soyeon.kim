package org.sopt.global.error;

import lombok.extern.slf4j.Slf4j;
import org.sopt.global.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException ex) {
        log.error("BusinessException 발생: {}", ex.getMessage());
        ErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus())
                   .body(ApiResponse.error(errorCode));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        log.error("MethodArgumentTypeMismatchException 발생: {}", ex.getMessage());

        return ResponseEntity.status(GlobalErrorCode.BAD_REQUEST.getStatus())
                   .body(ApiResponse.error(GlobalErrorCode.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException 발생: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body(ApiResponse.error(GlobalErrorCode.BAD_REQUEST));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(NoHandlerFoundException ex) {
        log.error("NoHandlerFoundException 발생: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body(ApiResponse.error(GlobalErrorCode.RESOURCE_NOT_FOUND));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnhandledException(Exception ex) {
        log.error("서버 내부 오류 발생: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(ApiResponse.error(GlobalErrorCode.INTERNAL_SERVER_ERROR));
    }
}
