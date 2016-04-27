/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.security.api.interceptor;

import com.pamarin.security.api.AccessToken;
import com.pamarin.security.api.annotation.Authorization;
import com.pamarin.security.api.exception.AuthorizationException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author jittagornp
 */
public class AccessTokenHandlerInterceptor extends HandlerInterceptorAdapter {

    private final AccessToken accessToken;

    public AccessTokenHandlerInterceptor(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    private boolean isEmpty(String token) {
        return token == null || token.isEmpty();
    }

    private boolean isHandlerMethod(Object handler) {
        return handler instanceof HandlerMethod;
    }

    private boolean dontHaveAnnotation(HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        Authorization authorization = method.getAnnotation(Authorization.class);
        if (authorization == null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!isHandlerMethod(handler)) {
            return true;
        }

        if (dontHaveAnnotation((HandlerMethod) handler)) {
            return true;
        }

        String token = request.getHeader("x-auth-token");
        if (isEmpty(token)) {
            throw new AuthorizationException("require access token.");
        }

        request.setAttribute("principal", accessToken.verify(token));
        return true;
    }

}
