package com.sejun.board.support;

public class ApiResponse<T> {

    private final ResultType resultType;

    private final T data;

    private final String errorMessage;

    public ApiResponse(ResultType resultType, T data, String errorMessage) {
        this.resultType = resultType;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public static ApiResponse success() {
        return new ApiResponse(ResultType.SUCCESS, null, null);

    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse(ResultType.SUCCESS, data, null);
    }

    public static ApiResponse error() {
        return new ApiResponse(ResultType.ERROR, null, null);

    }

    public static ApiResponse error(String errorMessage) {
        return new ApiResponse(ResultType.SUCCESS, null, null);
    }
}
