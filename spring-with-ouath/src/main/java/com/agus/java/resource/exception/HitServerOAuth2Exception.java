package com.agus.java.resource.exception;

public class HitServerOAuth2Exception extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public HitServerOAuth2Exception(final String errorParam) {
        super("Error OAuth2Server : ".concat(errorParam));
    }

}
