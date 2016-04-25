/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.security.api;

import java.util.Objects;

/**
 *
 * @author jittagornp
 */
public class Principal {

    private final String userId;

    public Principal(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.userId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Principal other = (Principal) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return true;
    }

}
