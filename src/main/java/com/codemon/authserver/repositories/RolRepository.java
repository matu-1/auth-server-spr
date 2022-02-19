package com.codemon.authserver.repositories;

import com.codemon.authserver.dtos.PermisoDto;
import com.codemon.authserver.models.Rol;

import java.util.List;

public interface RolRepository extends CrudRepository<Rol> {
    List<PermisoDto> findPermisos(Long rolId);
}
