package io.github.tiagoferreira.service;

import io.github.tiagoferreira.bean.BaseBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IServiceBase <T extends BaseBean, ID extends Serializable> {

    Optional<T> findById(ID id);

    List<T> findAll();

    ID insert(T bean);

    void update(T bean);

    void delete(T bean);

}
