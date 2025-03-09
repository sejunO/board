package com.sejun.board.support;

import com.sejun.board.error.ErrorCode;

public enum WebErrorType {
    USER_NOT_AUTHORIZED(ErrorCode.E1000, "로그인이 필요합니다");
    
    
    private final ErrorCode errorCode;
    private final String message;
    
    WebErrorType(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
    
    public ErrorCode getErrorCode() {
        return errorCode;
    }
    
    public String getMessage() {
        return message;
    }
}
