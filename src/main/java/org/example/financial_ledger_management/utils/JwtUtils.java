package org.example.financial_ledger_management.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private final String jwtSecret = "a2ba371ab0b6229ee5cfd2b629e0fca93c1f729446c4723d0bcdac08ad16e333fb9a9ec4b2aa0074054184778b4bf069035060feb1ea2d4db9058cc3b7281269031e012f36c4c1b5aed03a58abb5f3ec25064c7ab504d30efdf0d9853edb4cef25b3bc2563a765d9041787e482f6d9d90a717ea6d48afab8be171ef8e1ae6315030990106f2beb2be04ce3bebca6c67a7137e0e273e518b506d5e6d4d069076143806f687e9e04c9103977f5b07bca1dcc328b54df57f8a2d4c5e9e71c2a9bed71443ebe60a9276a9c26b6ef0f339f58fe07b573c4ba39965dc8197539a461a63beb823d5f026f83e29f4b9ca2c461b857e263d6f9910e0ed17ad2e977ab58e3";
    private final long jwtExpirationMs = 86400000; // 24 часа.

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Логирование ошибок можно добавить здесь
            return false;
        }
    }
}
