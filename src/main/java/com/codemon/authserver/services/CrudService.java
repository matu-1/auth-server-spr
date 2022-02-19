package com.codemon.authserver.services;

import com.codemon.authserver.models.BaseEntity;

import java.util.List;

public interface CrudService<TEntity extends BaseEntity, TDto> {
    List<TEntity> all();
    TEntity findById(Long id);
    TEntity create(TEntity entity);
    TEntity create(TDto dto);
    TEntity update(Long id, TEntity entity);
    TEntity update(Long id, Object dto);
    TEntity remove(Long id);
}
