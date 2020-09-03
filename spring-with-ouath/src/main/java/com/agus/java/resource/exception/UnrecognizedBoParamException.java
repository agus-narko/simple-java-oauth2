package com.agus.java.resource.exception;

public class UnrecognizedBoParamException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UnrecognizedBoParamException(final String errorParam) {
        super("Bo Class not found : ".concat(errorParam));
    }

}
