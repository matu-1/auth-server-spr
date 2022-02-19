package com.codemon.authserver.configs;

import com.codemon.authserver.services.RolService;
import com.codemon.authserver.utils.CustomAccessDeniedHandler;
import com.codemon.authserver.utils.CustomAuthenticationEntryPoint;
import com.codemon.authserver.utils.JwtAuthorizationFilter;
import com.codemon.authserver.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtUtil jwtUtil;
    private final RolService rolService;

    SpringSecurityConfig(JwtUtil jwtUtil, RolService rolService){
        this.jwtUtil = jwtUtil;
        this.rolService = rolService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()
            .antMatchers("/auth/login", "/auth/register", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .anyRequest().authenticated()
            .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler()) //no funciona
            .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
            .and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtUtil, rolService));
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }
}
