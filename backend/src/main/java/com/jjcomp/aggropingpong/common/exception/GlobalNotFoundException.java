package com.jjcomp.aggropingpong.common.exception;

public class GlobalNotFoundException extends RuntimeException {

    public GlobalNotFoundException() {
        super();
    }

    public GlobalNotFoundException(String message) {
        super(message);
    }

    public GlobalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
