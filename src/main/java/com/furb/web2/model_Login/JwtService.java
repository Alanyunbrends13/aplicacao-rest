package com.furb.web2.model_Login;


import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET =
            "minha-chave-super-secreta-com-mais-de-32-caracteres";

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public String gerarToken(String usuario) {

        return Jwts.builder()
                .subject(usuario)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + (5 * 60 * 1000)
                        )
                )
                .signWith(key)
                .compact();
    }

    public String validarToken(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
}