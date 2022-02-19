package com.codemon.authserver.repositories;

import com.codemon.authserver.models.Permiso;
import com.codemon.authserver.models.Rol;

import java.util.List;

public interface RolRepository extends CrudRepository<Rol> {
    List<Permiso> findPermisos(Long rolId);
}
