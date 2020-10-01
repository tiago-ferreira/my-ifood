package io.github.tiagoferreira.resource;

import io.github.tiagoferreira.bean.LocalizacaoBean;
import io.github.tiagoferreira.service.LocalizacaoService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/localizacao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Localização")
public class LocalizacaoResource {

    @Inject
    LocalizacaoService localizacaoService;

    @GET
    public List<LocalizacaoBean> findAll() {
        return localizacaoService.findAll();
    }

    @POST
    public LocalizacaoBean save(LocalizacaoBean localizacaoBean) {
        return localizacaoService.save(localizacaoBean);
    }

    @PUT
    @Path("/{id}")
    public LocalizacaoBean update(@PathParam("id") Long id,  LocalizacaoBean localizacaoBean) {
        return localizacaoService.update(id, localizacaoBean);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        localizacaoService.delete(id);
    }


}
