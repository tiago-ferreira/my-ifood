package io.github.tiagoferreira.service;

import io.github.tiagoferreira.bean.BaseBean;
import io.github.tiagoferreira.entity.BaseEntity;
import io.github.tiagoferreira.mapper.GenericMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class GenericService <E extends BaseEntity, B extends BaseBean, ID extends Serializable, MAPPER extends GenericMapper<E, B>> implements IServiceBase<B,ID>{
//    private final Logger logger = Logger.getLogger(this.getClass());

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

//            this.logger.debug(genericTypes[0].getClass().getName());
            this.typeOfEntity = (Class<E>) genericTypes[0];
            this.typeOfBean = (Class<B>) genericTypes[1];
            this.typeOfIdEntity = (Class<ID>) genericTypes[2];
            this.mapper = Mappers.getMapper((Class<MAPPER>) genericTypes[3]);
        } catch (RuntimeException e) {
//            this.logger.error(e.getMessage(), e);
            throw e;
        }

//        if (this.logger.isInfoEnabled()) {
//            this.logger.info(String.format("Creating a service with the bean [%s], entity [%s], key bean [%s] and key entity [%s].",
//                    getTypeOfBean(), getTypeOfEntity(), getMapper(), getTypeOfKeyEntity()));
//        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    // ABSTRACT METHODS
    /////////////////////////////////////////////////////////////////////////////////

    protected abstract PanacheRepository<E> getConcreteRepository();

    /////////////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    // ///////////////////////////////////////////////////////////////////////////////

    public B save(B bean) {
        E entity = (E) mapper.toEntity(bean);
        getConcreteRepository().persist(entity);
        return (B) mapper.toBean(entity);
    }

//    public B update(B bean) {
//        E entity = (E) mapper.toEntity(bean);
//        entity = getConcreteRepository().update(entity);
//        return (B) mapper.toBean(entity);
//    }
//
//    public void delete(ID id) {
//        E entity = (E) getConcreteRepository().find(this.typeOfEntity, id);
//        getConcreteRepository().remove(entity);
//    }

//    public Optional<B> findById(ID id) {
//        E entity = getConcreteRepository().find(this.typeOfEntity, id);
//        if (entity != null) {
//            return (Optional<B>) Optional.of(mapper.toBean(entity));
//        }
//        return Optional.<B>empty();
//    }
//
//    public Optional<B> findBCompositeKey(B bean) {
//        E entity = (E) mapper.toEntity(bean);
//        entity = getConcreteRepository().find(this.typeOfEntity, entity);
//        if (entity != null) {
//            return (Optional<B>) Optional.of(mapper.toBean(entity));
//        }
//        return Optional.<B>empty();
//    }
//
//    public List<B> findAll() {
//        List resultList = getConcreteRepository().createQuery("from " + this.typeOfEntity.getSimpleName() + " e").getResultList();
//        if (resultList != null && !resultList.isEmpty()) {
//            return (List<B>) mapper.toBeans(resultList);
//        }
//        return Collections.emptyList();
//    }


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
