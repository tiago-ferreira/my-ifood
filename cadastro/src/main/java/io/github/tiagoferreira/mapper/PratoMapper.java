package io.github.tiagoferreira.mapper;

import io.github.tiagoferreira.bean.PratoBean;
import io.github.tiagoferreira.entity.Prato;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface PratoMapper extends GenericMapper<Prato, PratoBean>{
}
