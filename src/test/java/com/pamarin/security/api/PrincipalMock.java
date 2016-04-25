/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.security.api;

import static com.pamarin.security.api.UserMock.USER_ID;

/**
 *
 * @author jittagornp
 */
public class PrincipalMock {

    public static Principal newPrincipal() {

        return new Principal(USER_ID);

    }

}
