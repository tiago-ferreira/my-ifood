package io.github.tiagoferreira.repository;

import io.github.tiagoferreira.entity.Restaurante;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RestauranteRepository implements PanacheRepository<Restaurante> {
}
