package com.codemon.authserver.utils;

import com.codemon.authserver.models.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jackson2.SimpleGrantedAuthorityMixin;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {
    @Value("${spring.security.jwt.secret}")
    private String secret;

    @Value("${spring.security.jwt.expiration}")
    private long expiration;

    public String generate(Usuario usuario) {
        Claims claims = Jwts.claims();
        claims.put("email", usuario.getEmail());
        claims.put("rolId", usuario.getRolId());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(usuario.getId().toString())
                .signWith(SignatureAlgorithm.HS512, secret)
                .setIssuedAt(new Date())
                .setExpiration( new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    public Claims getBody(String token){
       return Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public static List<GrantedAuthority> parseAuthorities(Object object) throws JsonProcessingException {
       return Arrays.asList(new ObjectMapper()
                .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
                .readValue(object.toString(), SimpleGrantedAuthority[].class));
    }
}
