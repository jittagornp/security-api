/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.security.api.exception;

/**
 *
 * @author jittagornp
 */
public class AuthorizationException extends PamarinException {

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(Throwable cause) {
        super(cause);
    }

}
