package com.agus.java.resource.exception;

public class ApprovalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApprovalException(final String errorParam) {
        super("Cannot find any approval for role id : ".concat(errorParam));
    }
}
