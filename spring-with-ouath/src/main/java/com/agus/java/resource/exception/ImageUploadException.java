package com.agus.java.resource.exception;

public class ImageUploadException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ImageUploadException(final String errorParam) {
        super("Only image files of type ".concat(errorParam).concat(" are allowed "));
    }

}
