package com.codemon.authserver.services;

import com.codemon.authserver.dtos.CreateUsuarioDto;
import com.codemon.authserver.models.Usuario;
import com.codemon.authserver.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp extends CrudServiceImp<Usuario, CreateUsuarioDto> implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository, ModelMapper mapper) {
        super(usuarioRepository, mapper);
        this.usuarioRepository = usuarioRepository;
    }
}
