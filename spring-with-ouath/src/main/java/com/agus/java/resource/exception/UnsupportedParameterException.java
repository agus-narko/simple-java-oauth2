package com.agus.java.resource.exception;


public class UnsupportedParameterException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public UnsupportedParameterException(final String errorParam) {
        super("Unsupported paramaters : ".concat(errorParam));
    }

}
