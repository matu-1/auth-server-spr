package com.codemon.authserver.repositories.imps;

import com.codemon.authserver.repositories.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional
public class CrudRepositoryImp<TEntity> implements CrudRepository<TEntity> {
    @PersistenceContext
    protected EntityManager manager;
    private  Class<TEntity> entityClass;

    public CrudRepositoryImp(){
        entityClass = (Class<TEntity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    };

    @Override
    public List<TEntity> all() {
        String query = "select g from " + entityClass.getSimpleName() + " g";
        return (List<TEntity>) this.manager.createQuery(query).getResultList();
    }

    @Override
    public TEntity findById(Long id) {
        return this.manager.find(entityClass, id);
    }

    @Override
    public TEntity create(TEntity tEntity) {
        this.manager.persist(tEntity);
        return tEntity;
    }

    @Override
    public TEntity update(TEntity tEntity) {
       this.manager.merge(tEntity);
       return tEntity;
    }

    @Override
    public TEntity remove(TEntity tEntity) {
        this.manager.remove(tEntity);
        return tEntity;
    }

    @Override
    public TEntity remove(Long id) {
        TEntity data = this.findById(id);
        this.manager.remove(data);
        return data;
    }

    @Override
    public Class<TEntity> entityClass() {
        return this.entityClass;
    }

    @Override
    public EntityManager getManager() {
        return manager;
    }
}
