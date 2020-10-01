package io.github.tiagoferreira.resource;

import io.github.tiagoferreira.bean.PratoBean;
import io.github.tiagoferreira.bean.RestauranteBean;
import io.github.tiagoferreira.service.PratoService;
import io.github.tiagoferreira.service.RestauranteService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/reataurantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Restaurantes")
public class RestauranteResource {

    @Inject
    RestauranteService restauranteService;

    @Inject
    PratoService pratoService;


    @GET
    public List<RestauranteBean> buscaTodosRestaurantes() {
        return restauranteService.findAll();
    }

    @POST
    public RestauranteBean salvaRestaurante(RestauranteBean restauranteBean) {
        return restauranteService.save(restauranteBean);
    }

    @PUT
    @Path("/{id}")
    public RestauranteBean altearaRestaurante(@PathParam("id") Long id,  RestauranteBean restauranteBean) {
        return restauranteService.update(id, restauranteBean);
    }

    @DELETE
    @Path("/{id}")
    public void deletaRestaurante(@PathParam("id") Long id) {
        restauranteService.delete(id);
    }

    @GET
    @Path("/{idRestaurante}/pratos")
    public List<PratoBean> buscaTodosPratos(@PathParam("idRestaurante") Long idRestaurante) {
        return pratoService.buscaTodosPratos(idRestaurante);
    }

    @POST
    @Path("/{idRestaurante}/pratos")
    public PratoBean salvaPrato(@PathParam("idRestaurante") Long idRestaurante, PratoBean pratoBean) {
        return pratoService.salvaPratoDoRestaurante(idRestaurante, pratoBean);
    }

    @PUT
    @Path("/{idRestaurante}/pratos/{idPrato}")
    public PratoBean alteraPrato(@PathParam("idRestaurante") Long idRestaurante, @PathParam("idPrato") Long idPrato,  PratoBean pratoBean) {
        return pratoService.alteraPratoDoRestaurante(idRestaurante, idPrato, pratoBean);
    }

    @DELETE
    @Path("/{idRestaurante}/pratos/{idPrato}")
    public void deletaPrato(@PathParam("idRestaurante") Long idRestaurante, @PathParam("idPrato") Long idPrato) {
        pratoService.deletaPratoDoRestaurante(idRestaurante, idPrato);
    }
}
