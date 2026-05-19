package com.example.aiworkflowback.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
  public static final String SECRET_KEY = "aiworkflowUserSecretKey1234567890ABCDEF";
  public static final Long EXPIRATION_TIME = (long) (1000 * 60 * 60 * 24);

  private static SecretKey getSingerKey() {
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
  }

  public static String generateToken(String username) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("username", username);
    claims.put("created", new Date());

    return Jwts.builder().claims(claims)
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(getSingerKey())
        .compact();
  }

  public static Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
        .verifyWith(getSingerKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }
  public static Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  public static Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

}
