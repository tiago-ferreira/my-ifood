package io.github.tiagoferreira.service;

import io.github.tiagoferreira.bean.LocalizacaoBean;
import io.github.tiagoferreira.entity.Localizacao;
import io.github.tiagoferreira.mapper.GenericMapper;
import io.github.tiagoferreira.mapper.LocalizacaoMapper;
import io.github.tiagoferreira.repository.LocalizacaoRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LocalizacaoService extends GenericService<Localizacao, LocalizacaoBean, Long>{

    @Inject
    LocalizacaoRepository localizacaoRepository;

    @Inject
    LocalizacaoMapper localizacaoMapper;

    public LocalizacaoService() {
        super(Localizacao.class, LocalizacaoBean.class, Long.class);
    }

    @Override
    protected PanacheRepositoryBase<Localizacao, Long> getConcreteRepository() {
        return localizacaoRepository;
    }

    @Override
    protected GenericMapper<Localizacao, LocalizacaoBean> getMapper() {
        return localizacaoMapper;
    }
}
