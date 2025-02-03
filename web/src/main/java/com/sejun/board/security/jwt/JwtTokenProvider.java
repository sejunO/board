package com.sejun.board.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey secretKey;

    @Value("${jwt.expiration}")
    private Duration validity;

    @PostConstruct
    private void getSigningKey() {
        secretKey =  Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * 토큰 생성
     * @param username
     * @param roles
     * @return
     */
    public String createToken(String username, String roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles); // 사용자 역할 등 추가 정보 삽입

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + validity.toMillis());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * JWT 토큰 검증
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            // 만료 시간 확인
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            // 유효하지 않은 토큰인 경우 false 반환
            return false;
        }
    }



    /**
     * JWT 토큰에서 사용자 이름(Subject) 추출
     * @param token
     * @return
     */
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
