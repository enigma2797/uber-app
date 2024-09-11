package com.project.uber.security;

import com.project.uber.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    public SecretKey getSecretKey()
    {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user)
    {

        String token = Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail() )
                .claim("roles", user.getRoles()!=null?user.getRoles().toString():"")

                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 10*1000*60))
                .signWith(getSecretKey())
                .compact();

        return token;

    }

    public String generateRefreshToken(User user)
    {

        String token = Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 1000L*60*60*24*30*6))
                .signWith(getSecretKey())
                .compact();

        return token;

    }

    public Long getUserFromToken(String token)
    {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }

}
