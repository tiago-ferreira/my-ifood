package io.github.tiagoferreira.mapper;

import io.github.tiagoferreira.bean.LocalizacaoBean;
import io.github.tiagoferreira.entity.Localizacao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface LocalizacaoMapper extends GenericMapper<Localizacao, LocalizacaoBean>{
}
