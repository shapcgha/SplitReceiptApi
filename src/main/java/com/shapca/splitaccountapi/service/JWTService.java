package com.shapca.splitaccountapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.shapca.splitaccountapi.domain.User;
import org.springframework.stereotype.Service;

@Service
public class JWTService {
    private static final String SECRET = "shapca";

    public String createJWT(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withClaim("userId", user.getId())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Can't create JWT");
        }
    }

    public Long decodeJWT(String jwt) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            return verifier.verify(jwt).getClaim("userId").asLong();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Can't decode JWT");
        }
    }
}
