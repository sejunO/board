package com.sejun.board.support;

public class WebException extends RuntimeException {
    
    private final WebErrorType webErrorType;
    
    public WebException(WebErrorType webErrorType) {
        this.webErrorType = webErrorType;
    }
}
