package com.agus.java.resource.exception;

public class InvalidParameterException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidParameterException(final String errorParam) {
        super("Invalid paramaters : ".concat(errorParam));
    }

}
