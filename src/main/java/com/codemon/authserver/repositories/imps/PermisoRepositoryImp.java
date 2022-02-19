package com.codemon.authserver.repositories.imps;

import com.codemon.authserver.models.Permiso;
import com.codemon.authserver.repositories.PermisoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PermisoRepositoryImp extends CrudRepositoryImp<Permiso> implements PermisoRepository {
}
