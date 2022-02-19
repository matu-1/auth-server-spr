package com.codemon.authserver.services.imps;

import com.codemon.authserver.models.BaseEntity;
import com.codemon.authserver.repositories.CrudRepository;
import com.codemon.authserver.services.CrudService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
public class CrudServiceImp<TEntity extends BaseEntity, TDto> implements CrudService<TEntity, TDto> {
    private final CrudRepository<TEntity> repository;
    protected final ModelMapper mapper;

    @Override
    public List<TEntity> all() {
        return repository.all();
    }

    @Override
    public TEntity findById(Long id) {
        TEntity entity = repository.findById(id);
        if(entity == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El registro no existe");
        return entity;
    }

    @Override
    public TEntity create(TEntity tEntity) {
        return repository.create(tEntity);
    }

    @Override
    public TEntity create(TDto tDto) {
        System.out.println(tDto);
        TEntity entity = mapper.map(tDto, repository.entityClass());
        System.out.println(entity);
        return repository.create(entity);
    }

    @Override
    public TEntity update(Long id, TEntity tEntity) {
        tEntity.setId(id);
        return this.update(id, (Object)tEntity);
    }

    @Override
    public TEntity update(Long id, Object dto) {
        TEntity entity = this.findById(id);
        mapper.map(dto, entity);
        return repository.update(entity);
    }

    @Override
    public TEntity remove(Long id) {
        TEntity entity = this.findById(id);
        return repository.remove(entity);
    }
}
