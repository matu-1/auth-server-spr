package com.codemon.authserver.services;

import com.codemon.authserver.dtos.CreateRolDto;
import com.codemon.authserver.dtos.PermisoDto;
import com.codemon.authserver.dtos.UpdateRolDto;
import com.codemon.authserver.models.Permiso;
import com.codemon.authserver.models.Rol;

import java.util.List;

public interface RolService extends CrudService<Rol, CreateRolDto> {
    List<PermisoDto> findPermisos(Long rolId);
    Rol createWithPermisos(CreateRolDto dto);
    Rol updateWithPermisos(Long id, UpdateRolDto dto);
}
