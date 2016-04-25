package com.pamarin.security.api;

/*
 *  Copyright 2016 Pamarin.com
 */
import com.pamarin.security.api.config.AppConfig;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author jittagornp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AccessTokenTest {

    @Autowired
    private AccessToken accessToken;

    @Test
    public void shouldBeEqualsAccessTokenImpl() {

        assertEquals("com.pamarin.security.api.AccessTokenImpl", accessToken.getClass().getName());

    }

    @Test
    public void sign() {

        AccessToken accTk = Mockito.mock(AccessToken.class);

        Principal principal = PrincipalMock.newPrincipal();
        String token = TokenMock.TOKEN;

        Mockito.when(accTk.sign(principal)).thenReturn(token);

        assertEquals(token, accTk.sign(principal));

    }

    @Test
    public void verify() {

        AccessToken accTk = Mockito.mock(AccessToken.class);

        Principal principal = PrincipalMock.newPrincipal();
        String token = TokenMock.TOKEN;

        Mockito.when(accTk.verify(token)).thenReturn(principal);

        assertEquals(principal, accTk.verify(token));

    }
}
