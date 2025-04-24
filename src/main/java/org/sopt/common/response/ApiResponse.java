package org.sopt.common.response;

public class ApiResponse<T> {

    private final int status;
    private final String message;
    private final T data;

    private ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(200, "요청이 성공적으로 처리되었습니다.", data);
    }

    public static <T> ApiResponse<T> created() {
        return new ApiResponse<>(201, "새로운 리소스 생성에 성공했습니다.", null);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}