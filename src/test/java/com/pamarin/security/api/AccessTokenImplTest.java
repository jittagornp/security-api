/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.security.api;

import com.pamarin.security.api.config.AppConfig;
import com.pamarin.security.api.exception.AuthorizationException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author jittagornp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AccessTokenImplTest {

    @Autowired
    private AccessTokenImpl accessTokenImpl;

    @Test(expected = NullPointerException.class)
    public void shouldBeThrowNullPointerException_whenPrincipalIsNull() {

        accessTokenImpl.sign(null);

    }
    
    @Test(expected = NullPointerException.class)
    public void shouldBeThrowNullPointerException_whenTokenIsNull() {

        accessTokenImpl.verify(null);

    }

    @Test
    public void shouldBeOk_whenSignPrincipal() {

        Principal principal = PrincipalMock.newPrincipal();

        String token = accessTokenImpl.sign(principal);
        String expected = TokenMock.TOKEN;

        assertThat(token).isEqualTo(expected);
    }
    
    @Test(expected = AuthorizationException.class)
    public void shouldBeThrowAuthorizationException() {
        
        String token = "00000000";
        
        Principal principal = accessTokenImpl.verify(token);
        Principal expected = PrincipalMock.newPrincipal();
        
        assertThat(principal).isEqualTo(expected);
        
    }

    @Test
    public void shouldBeOk_whenVerifyToken() {
        
        String token = TokenMock.TOKEN;
        
        Principal principal = accessTokenImpl.verify(token);
        Principal expected = PrincipalMock.newPrincipal();
        
        assertThat(principal).isEqualTo(expected);
        
    }
}
