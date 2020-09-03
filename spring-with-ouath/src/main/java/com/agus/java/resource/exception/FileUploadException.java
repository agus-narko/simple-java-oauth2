package com.agus.java.resource.exception;

public class FileUploadException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FileUploadException(final String errorParam) {
        super("Only files of type ".concat(errorParam).concat(" are allowed "));
    }

}
