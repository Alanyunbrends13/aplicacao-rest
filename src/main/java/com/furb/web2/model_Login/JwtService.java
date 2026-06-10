package com.furb.web2.model_Login;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private static final String SECRET = "minha-chave-super-secreta-com-mais-de-32-caracteres";
    
    public String gerarToken(String usuario){

        return Jwts.builder()
        .subject(usuario)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + (5 * 60 * 1000) ))
        .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
        .compact();
    }

}
