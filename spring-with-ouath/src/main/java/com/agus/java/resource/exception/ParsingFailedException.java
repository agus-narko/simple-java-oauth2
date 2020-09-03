package com.agus.java.resource.exception;

public class ParsingFailedException  extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ParsingFailedException(final String errorParam) {
        super("Parsing failed : ".concat(errorParam));
    }

}