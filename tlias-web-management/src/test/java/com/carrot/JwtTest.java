package com.carrot;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGenerateJwt() {
        Map<String, Object> claims = Map.of("username", "carrot", "name", "carrot", "password", "123456");
        String jwts = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "Y2Fycm90")
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(jwts);

    }

    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiY2Fycm90IiwidXNlcm5hbWUiOiJjYXJyb3QiLCJwYXNzd29yZCI6IjEyMzQ1NiIsImV4cCI6MTc1MzE3NzkwNn0.Yeq960INRdMjewcMidR34MYc1opAkqCHqyA1Z8dp50s";
        Claims claims = Jwts.parser()
                .setSigningKey("Y2Fycm90")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);

    }
}
