package com.sejun.board.support;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(ResultType resultType, T data, String errorMessage) {

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
