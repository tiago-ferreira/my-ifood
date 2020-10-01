package io.github.tiagoferreira.service;

import io.github.tiagoferreira.bean.PratoBean;
import io.github.tiagoferreira.bean.RestauranteBean;
import io.github.tiagoferreira.entity.Prato;
import io.github.tiagoferreira.entity.Restaurante;
import io.github.tiagoferreira.mapper.GenericMapper;
import io.github.tiagoferreira.mapper.PratoMapper;
import io.github.tiagoferreira.repository.PratoRepository;
import io.github.tiagoferreira.repository.RestauranteRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PratoService extends GenericService<Prato, PratoBean, Long> {

    @Inject
    PratoRepository pratoRepository;

    @Inject
    PratoMapper pratoMapper;

    @Inject
    RestauranteRepository restauranteRepository;

    public PratoService() {
        super(Prato.class, PratoBean.class, Long.class);
    }

    @Override
    protected PanacheRepositoryBase<Prato, Long> getConcreteRepository() {
        return pratoRepository;
    }

    @Override
    protected GenericMapper<Prato, PratoBean> getMapper() {
        return pratoMapper;
    }

    @Transactional
    public PratoBean salvaPratoDoRestaurante(Long idRestaurante, PratoBean pratoBean) {
        Prato prato = pratoMapper.toEntity(pratoBean);
        Restaurante restaurante = restauranteRepository.findById(idRestaurante);
        prato.setRestaurante(restaurante);
        pratoRepository.persist(prato);
        return pratoMapper.toBean(prato);
    }

    @Transactional
    public PratoBean alteraPratoDoRestaurante(Long idRestaurante, Long idPrato, PratoBean pratoBean) {
        Prato prato = pratoRepository.findById(idPrato);
        Restaurante restaurante = restauranteRepository.findById(idRestaurante);
        prato.setRestaurante(restaurante);
        pratoMapper.updateEntity(prato, pratoBean);
        pratoRepository.persist(prato);
        return pratoMapper.toBean(prato);
    }


    @Transactional
    public void deletaPratoDoRestaurante(Long idRestaurante, Long idPrato) {
        Optional<Prato> pratoOptional = pratoRepository.find("restaurante.id = :idRestaurante and id = :idPrato",
                Parameters.with("idRestaurante", idRestaurante).and("idPrato", idPrato).map()).singleResultOptional();
        pratoOptional.ifPresent(pratoRepository::delete);
    }

    public List<PratoBean> buscaTodosPratos(Long idRestaurante) {
        List<Prato> pratos = pratoRepository.find("restaurante.id", idRestaurante).list();
        return pratoMapper.toBeans(pratos);
    }
}
