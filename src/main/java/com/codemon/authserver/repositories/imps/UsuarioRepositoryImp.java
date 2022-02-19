package com.codemon.authserver.repositories.imps;

import com.codemon.authserver.models.Usuario;
import com.codemon.authserver.repositories.UsuarioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryImp extends CrudRepositoryImp<Usuario> implements UsuarioRepository {
    @Override
    public Usuario findByEmail(String email) {
      return findByEmail(email, 0L);
    }

    @Override
    public Usuario findByEmail(String email, Long id) {
        String query = "select u from Usuario u where u.email = :email and u.id <> :id";
        try {
            return (Usuario) manager.createQuery(query)
                    .setParameter("id", id)
                    .setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
