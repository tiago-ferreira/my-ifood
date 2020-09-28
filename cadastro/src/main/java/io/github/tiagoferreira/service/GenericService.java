package io.github.tiagoferreira.service;

import io.github.tiagoferreira.bean.BaseBean;
import io.github.tiagoferreira.entity.BaseEntity;
import io.github.tiagoferreira.mapper.GenericMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class GenericService <E extends BaseEntity, B extends BaseBean, ID extends Serializable, MAPPER extends GenericMapper<E, B>> {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final Class<B> typeOfBean;

    private final Class<E> typeOfEntity;

    private final Class<ID> typeOfIdEntity;

    private final GenericMapper mapper;

    /////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    /////////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("unchecked")
    public GenericService() {
        try {
            ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
            Type[] genericTypes = parameterizedType.getActualTypeArguments();

            this.logger.debug(genericTypes[0].getClass().getName());
            this.typeOfEntity = (Class<E>) genericTypes[0];
            this.typeOfBean = (Class<B>) genericTypes[1];
            this.typeOfIdEntity = (Class<ID>) genericTypes[2];
            this.mapper = Mappers.getMapper((Class<MAPPER>) genericTypes[3]);
        } catch (RuntimeException e) {
            this.logger.error(e.getMessage(), e);
            throw e;
        }

        if (this.logger.isInfoEnabled()) {
            this.logger.info(String.format("Creating a service with the bean [%s], entity [%s], key bean [%s] and key entity [%s].",
                    getTypeOfBean(), getTypeOfEntity(), getMapper(), getTypeOfIdEntity()));
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    // ABSTRACT METHODS
    /////////////////////////////////////////////////////////////////////////////////

    protected abstract PanacheRepositoryBase<E, ID> getConcreteRepository();

    /////////////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    // ///////////////////////////////////////////////////////////////////////////////

    public B save(B bean) {
        E entity = (E) mapper.toEntity(bean);
        getConcreteRepository().persist(entity);
        return (B) mapper.toBean(entity);
    }

    public B update(ID id, B bean) {
        E entity = getConcreteRepository().findById(id);
        mapper.updateEntity(entity, bean);
        getConcreteRepository().persist(entity);
        return (B) mapper.toBean(entity);
    }

    public void delete(ID id) {
        E entity = getConcreteRepository().findById(id);
        getConcreteRepository().delete(entity);
    }

    public Optional<B> findById(ID id) {
        E entity = getConcreteRepository().findById(id);
        if (entity != null) {
            return (Optional<B>) Optional.of(mapper.toBean(entity));
        }
        return Optional.<B>empty();
    }


    public List<B> findAll() {
        List<E> resultList = getConcreteRepository().findAll().list();
        if (resultList != null && !resultList.isEmpty()) {
            return (List<B>) mapper.toBeans(resultList);
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

    public final GenericMapper getMapper() {
        return this.mapper;
    }
}
