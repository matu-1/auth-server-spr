package com.codemon.authserver.repositories.imps;

import com.codemon.authserver.models.Usuario;
import com.codemon.authserver.repositories.UsuarioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryImp extends CrudRepositoryImp<Usuario> implements UsuarioRepository {
    @Override
    public Usuario findByEmail(String email) {
        String query = "select u from Usuario u where u.email = :email";
        try {
            return (Usuario) manager.createQuery(query).setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
