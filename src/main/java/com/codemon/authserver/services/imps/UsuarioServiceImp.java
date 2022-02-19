package com.codemon.authserver.services.imps;

import com.codemon.authserver.dtos.CreateUsuarioDto;
import com.codemon.authserver.dtos.UpdateRolDto;
import com.codemon.authserver.dtos.UpdateUsuarioDto;
import com.codemon.authserver.models.Rol;
import com.codemon.authserver.models.Usuario;
import com.codemon.authserver.repositories.RolRepository;
import com.codemon.authserver.repositories.UsuarioRepository;
import com.codemon.authserver.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioServiceImp extends CrudServiceImp<Usuario, CreateUsuarioDto> implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository, ModelMapper mapper, RolRepository rolRepository) {
        super(usuarioRepository, mapper);
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public Usuario create(CreateUsuarioDto dto) {
        validDto(dto.getEmail(), dto.getRolId(), 0L);
        return super.create(dto);
    }

    @Override
    public Usuario update(Long id, Object dto) {
        UpdateUsuarioDto update = (UpdateUsuarioDto) dto;
        validDto(update.getEmail(), update.getRolId(), id);
        return super.update(id, dto);
    }

    private void validDto(String email, Long rolId, Long id){
        Usuario usuario = usuarioRepository.findByEmail(email, id);
        if(usuario != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email ya existe");
        Rol rol = rolRepository.findById(rolId);
        if(rol == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El rolId no existe");
    }
}
