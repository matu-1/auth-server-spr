package com.codemon.authserver.repositories.imps;

import com.codemon.authserver.dtos.PermisoDto;
import com.codemon.authserver.models.Permiso;
import com.codemon.authserver.models.Rol;
import com.codemon.authserver.repositories.RolRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RolRepositoryImp extends CrudRepositoryImp<Rol> implements RolRepository {
    @Override
    public List<PermisoDto> findPermisos(Long rolId) {
        String query = "select new com.codemon.authserver.dtos.PermisoDto(rp.permisoId, rp.permiso.nombre) from RolPermiso rp where rp.rolId = :id";
        return manager.createQuery(query, PermisoDto.class).setParameter("id", rolId).getResultList();
    }
}
