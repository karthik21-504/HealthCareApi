package com.example.project.config;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.project.Model.ApplicationUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.token.validity}")
    private long expirationTime;

    public String generateToken(ApplicationUser user) {
        return Jwts.builder()
                .setSubject(user.getUser_name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token, ApplicationUser user) {
        return user.getUser_name().equals(extractUsername(token)) && !isTokenExpired(token);
    }
}
