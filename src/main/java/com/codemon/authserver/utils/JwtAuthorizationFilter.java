package com.codemon.authserver.utils;

import com.codemon.authserver.services.RolService;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private JwtUtil jwtUtil;
    private RolService rolService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, RolService rolService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.rolService = rolService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")){
            this.logger.error("No hay Authorization: " + header);
            chain.doFilter(request, response);
            return;
        }
        try {
            Claims body = jwtUtil.getBody(header.replace("Bearer ", ""));
            Long rolId = body.get("rolId", Long.class);
            List<SimpleGrantedAuthority> simpleGrantedAuthorities = rolService
                    .findPermisos(rolId).stream().map(item -> new SimpleGrantedAuthority("ROLE_" + item.getNombre()))
                    .collect(Collectors.toList());
            //List<GrantedAuthority> authorities = JwtUtil.parseAuthorities(body.get("authorities"));
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(body.getSubject(), null, simpleGrantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }catch (Exception e){
            this.logger.error("Error en la verificacion del TOKEN: " + e.getMessage());
        } finally {
            chain.doFilter(request, response);
        }
    }
}
