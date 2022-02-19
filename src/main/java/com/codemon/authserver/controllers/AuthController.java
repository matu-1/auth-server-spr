package com.codemon.authserver.controllers;

import com.codemon.authserver.dtos.CreateUsuarioDto;
import com.codemon.authserver.dtos.LoginDto;
import com.codemon.authserver.dtos.UsuarioTokenDto;
import com.codemon.authserver.services.AuthService;
import com.codemon.authserver.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    Response<UsuarioTokenDto> login(@Valid @RequestBody LoginDto dto){
        UsuarioTokenDto result = this.authService.login(dto);
        return new Response<>(result);
    }

    @PostMapping("register")
    Response<UsuarioTokenDto> register(@Valid @RequestBody CreateUsuarioDto dto){
        UsuarioTokenDto result = this.authService.register(dto);
        return new Response<>(result);
    }

    @Operation(summary = "Renueva el token")
    @GetMapping("renew")
    Response<UsuarioTokenDto> renew(Principal principal){
        System.out.println(principal);
        System.out.println(principal.getName());
        UsuarioTokenDto result = this.authService.renew(Long.parseLong(principal.getName()));
        return new Response<>(result);
    }
}
