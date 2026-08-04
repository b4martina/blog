package com.example.blog.service;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.bouncycastle.jcajce.BCFKSLoadStoreParameter;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

//jwt token part 2
@Service
public class JwtService {
    private final String SECRET_KEY = "keykeykeykeykeykeykeykey28282828";

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));



    public String generateToken(String username) {
        // Convert the string key into a valid SecretKey object
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 18000000)) // Updated from setExpiration()
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public String extractUsername(String token){
        return extractClaims(token).getSubject();}
    public boolean isTokenValid(String token, String username) {
        return username.equals(extractUsername(token))
                && extractClaims(token).getExpiration().after(new Date());}
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();}
}
