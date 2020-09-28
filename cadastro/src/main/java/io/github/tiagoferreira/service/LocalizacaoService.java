package io.github.tiagoferreira.service;

import io.github.tiagoferreira.bean.LocalizacaoBean;
import io.github.tiagoferreira.entity.Localizacao;
import io.github.tiagoferreira.mapper.LocalizacaoMapper;
import io.github.tiagoferreira.repository.LocalizacaoRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class LocalizacaoService extends GenericService<Localizacao, LocalizacaoBean, Long, LocalizacaoMapper>{

    @Inject
    private LocalizacaoRepository localizacaoRepository;

    @Override
    protected PanacheRepositoryBase<Localizacao, Long> getConcreteRepository() {
        return localizacaoRepository;
    }
}
