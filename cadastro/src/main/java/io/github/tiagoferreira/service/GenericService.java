package io.github.tiagoferreira.service;

import io.github.tiagoferreira.bean.BaseBean;
import io.github.tiagoferreira.entity.BaseEntity;
import io.github.tiagoferreira.mapper.GenericMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class GenericService<E extends BaseEntity, B extends BaseBean, ID extends Serializable> {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final Class<B> typeOfBean;

    private final Class<E> typeOfEntity;

    private final Class<ID> typeOfIdEntity;

    /////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    /////////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("unchecked")
    public GenericService(Class<E> typeOfEntity, Class<B> typeOfBean, Class<ID> typeOfIdEntity) {
        this.typeOfEntity = typeOfEntity;
        this.typeOfBean = typeOfBean;
        this.typeOfIdEntity = typeOfIdEntity;
    }

    /////////////////////////////////////////////////////////////////////////////////
    // ABSTRACT METHODS
    /////////////////////////////////////////////////////////////////////////////////

    protected abstract PanacheRepositoryBase<E, ID> getConcreteRepository();

    protected abstract GenericMapper<E, B> getMapper();


    /////////////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    // ///////////////////////////////////////////////////////////////////////////////

    @Transactional
    public B save(B bean) {
        E entity = (E) getMapper().toEntity(bean);
        getConcreteRepository().persist(entity);
        return (B) getMapper().toBean(entity);
    }

    @Transactional
    public B update(ID id, B bean) {
        E entity = getConcreteRepository().findById(id);
        getMapper().updateEntity(entity, bean);
        getConcreteRepository().persist(entity);
        return (B) getMapper().toBean(entity);
    }

    @Transactional
    public void delete(ID id) {
        E entity = getConcreteRepository().findById(id);
        getConcreteRepository().delete(entity);
    }

    public Optional<B> findById(ID id) {
        E entity = getConcreteRepository().findById(id);
        if (entity != null) {
            return (Optional<B>) Optional.of(getMapper().toBean(entity));
        }
        return Optional.<B>empty();
    }


    public List<B> findAll() {
        List<E> resultList = getConcreteRepository().findAll().list();
        if (resultList != null && !resultList.isEmpty()) {
            return (List<B>) getMapper().toBeans(resultList);
        }
        return Collections.emptyList();
    }


    public final Class<B> getTypeOfBean() {
        return this.typeOfBean;
    }

    public final Class<E> getTypeOfEntity() {
        return this.typeOfEntity;
    }

    public final Class<ID> getTypeOfIdEntity() {
        return this.typeOfIdEntity;
    }

}
