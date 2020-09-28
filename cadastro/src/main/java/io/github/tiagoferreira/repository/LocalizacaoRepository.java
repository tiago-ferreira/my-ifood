package io.github.tiagoferreira.repository;

import io.github.tiagoferreira.entity.Localizacao;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LocalizacaoRepository implements PanacheRepositoryBase<Localizacao, Long> {
}
