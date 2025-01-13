package org.example.financial_ledger_management.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Утилитный класс для работы с JWT.
 */
@Component
public class JwtUtils {

    /**
     * Секретный ключ для подписи JWT.
     */
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * Время жизни JWT в миллисекундах.
     */
    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    /**
     * Возвращает ключ для подписи JWT.
     *
     * @return ключ для подписи JWT
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Генерирует JWT для указанного имени пользователя.
     *
     * @param username имя пользователя
     * @return сгенерированный JWT
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Извлекает имя пользователя из JWT.
     *
     * @param token JWT
     * @return имя пользователя
     */
    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * Проверяет валидность JWT.
     *
     * @param token JWT
     * @return true, если JWT валиден, false в противном случае
     */
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

