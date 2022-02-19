package com.codemon.authserver.repositories;

import javax.persistence.EntityManager;
import java.util.List;

public interface CrudRepository<TEntity> {
    List<TEntity> all();
    TEntity findById(Long id);
    TEntity create(TEntity entity);
    TEntity update(TEntity entity);
    TEntity remove(TEntity entity);
    TEntity remove(Long id);
    Class<TEntity> entityClass();
    EntityManager getManager();
}
