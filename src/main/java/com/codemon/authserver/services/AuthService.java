package com.codemon.authserver.services;

import com.codemon.authserver.dtos.CreateUsuarioDto;
import com.codemon.authserver.dtos.LoginDto;
import com.codemon.authserver.dtos.UsuarioTokenDto;

public interface AuthService {
    UsuarioTokenDto login(LoginDto dto);
    UsuarioTokenDto register(CreateUsuarioDto dto);
    UsuarioTokenDto renew(Long id);
}
