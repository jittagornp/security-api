/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.security.api;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.pamarin.security.api.exception.AuthorizationException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author jittagornp
 */
@Component
public class AccessTokenImpl implements AccessToken {

    private static final String SECRET_KEY = /* */;
    private static final String PAYLOAD_KEY = /* */;
    public static final String KEY_NAME = /* */;
    private static final Algorithm ALGORITHM = /* */;
    private static final String DOT_ENCRYPT = /* */;

    private static final String TOKEN_HEADER = /* */;

    @Override
    public String sign(Principal principal) {

        if (principal == null) {
            throw new NullPointerException("require principal.");
        }

        Map<String, Object> data = new HashMap<>();
        data.put(PAYLOAD_KEY, principal);
        String token = new JWTSigner(SECRET_KEY).sign(data,
                new JWTSigner.Options()
                .setAlgorithm(ALGORITHM)
        );

        return token
                .substring(TOKEN_HEADER.length())
                .replace(".", DOT_ENCRYPT);
    }

    @Override
    public Principal verify(String token) {

        if (token == null) {
            throw new NullPointerException("require token.");
        }

        try {
            Map<String, Object> payload = new JWTVerifier(SECRET_KEY)
                    .verify(TOKEN_HEADER + token.replace(DOT_ENCRYPT, "."));
            return principalFromMap((Map<String, Object>) payload.get(PAYLOAD_KEY));
        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalStateException | IOException | SignatureException | JWTVerifyException ex) {
            throw new AuthorizationException(ex);
        }
    }

    private Principal principalFromMap(Map<String, Object> map) {
        return new Principal((String) map.get("userId"));
    }
}
