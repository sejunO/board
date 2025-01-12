package com.sejun.board.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final boolean success;
    private final T data;
    private final ErrorResponse error;
    private final LocalDateTime timestamp;

    private ApiResponse(boolean success, T data, ErrorResponse error) {
        this.success = success;
        this.data = data;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    // 성공 응답 생성 메서드
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    // 실패 응답 생성 메서드
    public static <T> ApiResponse<T> error(String message, String code) {
        return new ApiResponse<>(false, null, new ErrorResponse(code, message));
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public ErrorResponse getError() {
        return error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @param code    에러 코드
     * @param message 에러 메시지
     */
    public record ErrorResponse(String code, String message) {
    }
}
