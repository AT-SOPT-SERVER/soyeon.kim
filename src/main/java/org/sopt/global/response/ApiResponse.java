package org.sopt.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.sopt.global.error.ErrorCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(int code, String message, T data) {

    public ApiResponse(ErrorCode errorCode) {
        this(errorCode.getStatus().value(), errorCode.getMessage(), null);
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    public static ApiResponse<Void> ok(String message) {
        return new ApiResponse<>(200, message, null);
    }

    public static ApiResponse<Void> created(String message) {
        return new ApiResponse<>(201, message, null);
    }

    public static ApiResponse<Void> error(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode);
    }

    public static ApiResponse<Void> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}
