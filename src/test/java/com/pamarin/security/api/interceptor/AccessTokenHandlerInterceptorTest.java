/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.security.api.interceptor;

import com.pamarin.security.api.AccessToken;
import com.pamarin.security.api.TokenMock;
import com.pamarin.security.api.annotation.Authorization;
import com.pamarin.security.api.config.AppConfig;
import com.pamarin.security.api.exception.AuthenticationException;
import com.pamarin.security.api.exception.AuthorizationException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.method.HandlerMethod;

/**
 *
 * @author jittagornp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AccessTokenHandlerInterceptorTest {

    @Autowired
    private AccessToken accessToken;

    private AccessTokenHandlerInterceptor interceptor;

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void before() {

        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);

        interceptor = new AccessTokenHandlerInterceptor(accessToken);
    }

    @Test
    public void shouldBeTrue_whenTokenIsNull_andMethodIsNull() throws Exception {

        boolean pass = interceptor.preHandle(
                request,
                response,
                null
        );

        assertThat(pass).isEqualTo(true);

    }

    @Test
    public void shouldBeTrue_whenTokenIs00000000_andMethodIsNull() throws Exception {

        Mockito.when(request.getHeader("x-auth-token")).thenReturn("00000000");

        boolean pass = interceptor.preHandle(
                request,
                response,
                null
        );

        assertThat(pass).isEqualTo(true);

    }

    @Test
    public void shouldBeTrue_whenMethodIsNull() throws Exception {

        Mockito.when(request.getHeader("x-auth-token")).thenReturn(TokenMock.TOKEN);

        boolean pass = interceptor.preHandle(
                request,
                response,
                null
        );

        assertThat(pass).isEqualTo(true);

    }

    public String publicMethod() {
        return "OK";
    }

    @Authorization
    public String authorizationMethod() {
        return "OK";
    }

    @Test
    public void shouldBeTrue_whenTokenIsNull() throws Exception {

        HandlerMethod handlerMethod = Mockito.mock(HandlerMethod.class);
        Method method = getClass().getDeclaredMethod("publicMethod");
        Mockito.when(handlerMethod.getMethod()).thenReturn(method);

        boolean pass = interceptor.preHandle(
                request,
                response,
                handlerMethod
        );

        assertThat(pass).isEqualTo(true);

    }

    @Test
    public void shouldBeTrue_whenTokenIs00000000() throws Exception {

        Mockito.when(request.getHeader("x-auth-token")).thenReturn("00000000");

        HandlerMethod handlerMethod = Mockito.mock(HandlerMethod.class);
        Method method = getClass().getDeclaredMethod("publicMethod");
        Mockito.when(handlerMethod.getMethod()).thenReturn(method);

        boolean pass = interceptor.preHandle(
                request,
                response,
                handlerMethod
        );

        assertThat(pass).isEqualTo(true);

    }

    @Test
    public void shouldBeTrue() throws Exception {

        Mockito.when(request.getHeader("x-auth-token")).thenReturn(TokenMock.TOKEN);

        HandlerMethod handlerMethod = Mockito.mock(HandlerMethod.class);
        Method method = getClass().getDeclaredMethod("publicMethod");
        Mockito.when(handlerMethod.getMethod()).thenReturn(method);

        boolean pass = interceptor.preHandle(
                request,
                response,
                handlerMethod
        );

        assertThat(pass).isEqualTo(true);

    }

    @Test(expected = AuthenticationException.class)
    public void shouldBeThrowAuthenticationException_whenTokenIsNull_andAuthorizeMethod() throws Exception {

        HandlerMethod handlerMethod = Mockito.mock(HandlerMethod.class);
        Method method = getClass().getDeclaredMethod("authorizationMethod");
        Mockito.when(handlerMethod.getMethod()).thenReturn(method);

        boolean pass = interceptor.preHandle(
                request,
                response,
                handlerMethod
        );

    }

    @Test(expected = AuthorizationException.class)
    public void shouldBeThrowAuthorizationException_whenTokenIs00000000_andAuthorizeMethod() throws Exception {

        Mockito.when(request.getHeader("x-auth-token")).thenReturn("00000000");

        HandlerMethod handlerMethod = Mockito.mock(HandlerMethod.class);
        Method method = getClass().getDeclaredMethod("authorizationMethod");
        Mockito.when(handlerMethod.getMethod()).thenReturn(method);

        boolean pass = interceptor.preHandle(
                request,
                response,
                handlerMethod
        );

    }

    @Test
    public void shouldBeTrue_whenAuthorizeMethod() throws Exception {

        Mockito.when(request.getHeader("x-auth-token")).thenReturn(TokenMock.TOKEN);

        HandlerMethod handlerMethod = Mockito.mock(HandlerMethod.class);
        Method method = getClass().getDeclaredMethod("authorizationMethod");
        Mockito.when(handlerMethod.getMethod()).thenReturn(method);

        boolean pass = interceptor.preHandle(
                request,
                response,
                handlerMethod
        );

        assertThat(pass).isEqualTo(true);

    }
}
