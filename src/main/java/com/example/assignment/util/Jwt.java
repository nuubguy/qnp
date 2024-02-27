package com.example.assignment.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class Jwt {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String username) {
        Date expiration = new Date(System.currentTimeMillis() + 600000); // 1 minute expiration
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration)
                .signWith(SECRET_KEY)
                .compact();
    }

    public static boolean isValidToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
