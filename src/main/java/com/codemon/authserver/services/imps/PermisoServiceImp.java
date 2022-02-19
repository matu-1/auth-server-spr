package com.codemon.authserver.services.imps;

import com.codemon.authserver.dtos.CreatePermisoDto;
import com.codemon.authserver.models.Permiso;
import com.codemon.authserver.repositories.PermisoRepository;
import com.codemon.authserver.services.PermisoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PermisoServiceImp extends CrudServiceImp<Permiso, CreatePermisoDto> implements PermisoService {
    final PermisoRepository permisoRepository;

    public PermisoServiceImp(PermisoRepository repository, ModelMapper mapper) {
        super(repository, mapper);
        permisoRepository = repository;
    }
}
