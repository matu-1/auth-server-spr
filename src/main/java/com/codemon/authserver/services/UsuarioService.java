package com.codemon.authserver.services;

import com.codemon.authserver.dtos.CreateUsuarioDto;
import com.codemon.authserver.models.Usuario;

public interface UsuarioService extends CrudService<Usuario, CreateUsuarioDto> {
}
