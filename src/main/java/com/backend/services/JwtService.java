package com.backend.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.RegisteredClaims;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private final String SECRET = "secret";

    public String extractUsername(String token) {
        DecodedJWT decodedJWT = verifySinatureAndDecodeJwtToken(token);
        return decodedJWT.getSubject();
    }

    public String generateToken(UserDetails userDetails) {
        return JWT
                .create()
                .withIssuer("secpickup")
                .withSubject(userDetails.getUsername())
//                .withClaim(RegisteredClaims.EXPIRES_AT, new Date(System.currentTimeMillis() + 24 * 60 * 1000))
                .withClaim(RegisteredClaims.ISSUED_AT, new Date(System.currentTimeMillis()))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public DecodedJWT verifySinatureAndDecodeJwtToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .build()
                .verify(token);
        return decodedJWT;
    }
}
