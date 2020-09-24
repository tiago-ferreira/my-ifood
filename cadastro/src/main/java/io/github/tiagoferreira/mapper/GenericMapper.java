package io.github.tiagoferreira.mapper;

import io.github.tiagoferreira.bean.BaseBean;
import io.github.tiagoferreira.entity.BaseEntity;

import java.util.List;

public interface GenericMapper <E extends BaseEntity, B extends BaseBean>{
    E toEntity(B bean);
    B toBean(E entity);
    List<E> toEntities(List<B> beans);
    List<B> toBeans(List<E> entities);
}
