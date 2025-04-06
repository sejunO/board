package com.sejun.board.support;


public enum WebErrorType {
    USER_NOT_AUTHORIZED(WebErrorCode.WE100, "로그인이 필요합니다");
    
    
    private final WebErrorCode errorCode;
    private final String message;
    
    WebErrorType(WebErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
    
    public WebErrorCode getErrorCode() {
        return errorCode;
    }
    
    public String getMessage() {
        return message;
    }
}
