package io.github.tiagoferreira.mapper;

import io.github.tiagoferreira.bean.RestauranteBean;
import io.github.tiagoferreira.entity.Restaurante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper extends GenericMapper<Restaurante, RestauranteBean> {
}
