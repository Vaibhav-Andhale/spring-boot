package com.vaibhav.project.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
@Service
public class JWTService {
    private static final Logger logger= LoggerFactory.getLogger(JWTService.class);
    private static final String SECRET = "MY_SECRET_KEY_FOR_JWT_WHICH_IS_VERY_SECURE";
    private static final int SECRET_EXPIRY_TIME = 15;

    public String generateAccessToken(String userName) {
        return Jwts.builder()
                .subject(userName)
                .expiration(new Date(System.currentTimeMillis() + SECRET_EXPIRY_TIME * 60 * 1000))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), Jwts.SIG.HS256)
                .compact();
    }

    public String generateRefreshToken(String userName) {
        return Jwts.builder()
                .subject(userName)
                .expiration(new Date(System.currentTimeMillis() + (SECRET_EXPIRY_TIME * 60 * 1000))) // 7 days
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), Jwts.SIG.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public boolean isValid(String token, String userName) {
        String username = extractUsername(token);
        return username.equals(userName) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getExpiration().before(new Date());
    }
}
