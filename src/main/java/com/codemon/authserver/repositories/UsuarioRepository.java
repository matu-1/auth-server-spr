package com.codemon.authserver.repositories;

import com.codemon.authserver.models.Usuario;

public interface UsuarioRepository extends  CrudRepository<Usuario> {
    Usuario findByEmail(String email);
    Usuario findByEmail(String email, Long id);
}
