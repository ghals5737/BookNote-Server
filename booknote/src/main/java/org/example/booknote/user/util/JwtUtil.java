package org.example.booknote.user.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.booknote.user.domain.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    public String SECRET_KEY = "1sezxczxcret0kedway3basedaw647edwancghoddawed";

    public SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT 액세스 토큰 생성
    public String generateToken(User user, int duration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.id());
        claims.put("name", user.name());
        claims.put("email", user.email());
        claims.put("picture", user.picture());

        return createToken(claims, String.valueOf(user.id()), duration);
    }

    // JWT 리프레시 토큰 생성
    public String generateRefreshToken(User user, int duration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.id());

        return createToken(claims, String.valueOf(user.id()), duration);
    }

    public String createToken(Map<String, Object> claims, String subject, int duration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + duration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT 토큰에서 정보 추출
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .setAllowedClockSkewSeconds(60*60*24*30)  // 허용 시간 차이 60초
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractId(String token) {
        return extractAllClaims(token).getSubject();
    }

    // JWT 토큰이 만료되었는지 확인
    public Boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}


