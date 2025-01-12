package com.sejun.board.core.exception;

/**
 * 최상위 커스텀 Exception 클래스
 */
public abstract class BaseBusinessException extends RuntimeException {

    private final String errorCode;

    public BaseBusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
