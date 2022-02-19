package com.codemon.authserver.services.imps;

import com.codemon.authserver.dtos.CreateUsuarioDto;
import com.codemon.authserver.dtos.LoginDto;
import com.codemon.authserver.dtos.UsuarioTokenDto;
import com.codemon.authserver.models.Usuario;
import com.codemon.authserver.repositories.UsuarioRepository;
import com.codemon.authserver.services.AuthService;
import com.codemon.authserver.services.UsuarioService;
import com.codemon.authserver.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    final JwtUtil jwtUtil;
    final UsuarioService usuarioService;
    final UsuarioRepository usuarioRepository;
    final ModelMapper mapper;
    final BCryptPasswordEncoder bcrypt;

    @Override
    public UsuarioTokenDto login(LoginDto body) {
        Usuario usuario = usuarioRepository.findByEmail(body.getEmail());
        if(usuario == null || !bcrypt.matches(body.getPassword(), usuario.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales Incorrectas");
        return getUsuarioToken(usuario);
    }

    @Override
    public UsuarioTokenDto register(CreateUsuarioDto body) {
        Usuario usuario = usuarioService.create(body);
        return getUsuarioToken(usuario);
    }

    @Override
    public UsuarioTokenDto renew(Long id) {
        Usuario usuario = usuarioService.findById(id);
        return getUsuarioToken(usuario);
    }

    private UsuarioTokenDto getUsuarioToken(Usuario usuario){
        UsuarioTokenDto dto = mapper.map(usuario, UsuarioTokenDto.class);
        dto.setAccessToken(jwtUtil.generate(usuario));
        return dto;
    }
}
