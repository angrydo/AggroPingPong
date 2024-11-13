package com.jjcomp.aggropingpong.common.exception;

public class GlobalValidationException extends RuntimeException {

    public GlobalValidationException() {
        super();
    }

    public GlobalValidationException(String message) {
        super(message);
    }

    public GlobalValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
