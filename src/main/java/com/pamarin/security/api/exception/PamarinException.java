/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.security.api.exception;

/**
 *
 * @author jittagornp
 */
public class PamarinException extends RuntimeException {

    public PamarinException() {
        super();
    }

    public PamarinException(String message) {
        super(message);
    }

    public PamarinException(Throwable cause) {
        super(cause);
    }

}
