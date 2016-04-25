/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.security.api;

/**
 *
 * @author jittagornp
 */
public interface AccessToken {
    
    String sign(Principal principal);
    
    Principal verify(String token);
}
