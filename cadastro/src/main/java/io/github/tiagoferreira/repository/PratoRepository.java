package io.github.tiagoferreira.repository;

import io.github.tiagoferreira.entity.Prato;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PratoRepository implements PanacheRepository<Prato> {
}
