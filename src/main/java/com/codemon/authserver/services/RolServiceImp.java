package com.codemon.authserver.services;

import com.codemon.authserver.dtos.CreateRolDto;
import com.codemon.authserver.dtos.UpdateRolDto;
import com.codemon.authserver.models.Permiso;
import com.codemon.authserver.models.Rol;
import com.codemon.authserver.models.RolPermiso;
import com.codemon.authserver.repositories.RolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImp extends CrudServiceImp<Rol, CreateRolDto> implements RolService {
    private final RolRepository rolRepository;

    public RolServiceImp(RolRepository repository, ModelMapper mapper) {
        super(repository, mapper);
        rolRepository = repository;
    }

    @Override
    public List<Permiso> findPermisos(Long rolId) {
        return rolRepository.findPermisos(rolId);
    }

    @Override
    @Transactional
    public Rol createWithPermisos(CreateRolDto dto) {
        Rol rol = mapper.map(dto, Rol.class);
        this.create(rol);
        dto.getPermisosId().forEach(id -> {
            RolPermiso rolPermiso = new RolPermiso();
            rolPermiso.setRolId(rol.getId());
            rolPermiso.setPermisoId(id);
            this.rolRepository.getManager().persist(rolPermiso);
        });
        return rol;
    }

    @Override
    @Transactional
    public Rol updateWithPermisos(Long id, UpdateRolDto dto) {
        Rol rol = this.update(id, dto);
        rolRepository.getManager().createQuery("delete from RolPermiso where rolId = :id")
                        .setParameter("id", id).executeUpdate();
        dto.getPermisosId().forEach(item -> {
            RolPermiso rolPermiso = new RolPermiso();
            rolPermiso.setRolId(rol.getId());
            rolPermiso.setPermisoId(item);
            this.rolRepository.getManager().persist(rolPermiso);
        });
        return rol;
    }
}
