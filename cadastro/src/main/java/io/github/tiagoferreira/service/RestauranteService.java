package io.github.tiagoferreira.service;

import io.github.tiagoferreira.bean.RestauranteBean;
import io.github.tiagoferreira.entity.Restaurante;
import io.github.tiagoferreira.mapper.GenericMapper;
import io.github.tiagoferreira.mapper.RestauranteMapper;
import io.github.tiagoferreira.repository.RestauranteRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RestauranteService extends GenericService<Restaurante, RestauranteBean, Long>{

    @Inject
    RestauranteRepository restauranteRepository;

    @Inject
    RestauranteMapper restauranteMapper;

    public RestauranteService() {
        super(Restaurante.class, RestauranteBean.class, Long.class);
    }

    @Override
    protected PanacheRepositoryBase<Restaurante, Long> getConcreteRepository() {
        return restauranteRepository;
    }

    @Override
    protected GenericMapper<Restaurante, RestauranteBean> getMapper() {
        return restauranteMapper;
    }
}
