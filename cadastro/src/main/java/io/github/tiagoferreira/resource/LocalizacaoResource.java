package io.github.tiagoferreira.resource;

import io.github.tiagoferreira.bean.LocalizacaoBean;
import io.github.tiagoferreira.service.LocalizacaoService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/localizacao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Localização")
public class LocalizacaoResource {

    @Inject
    private LocalizacaoService localizacaoService;

    @GET
    public List<LocalizacaoBean> findAll() {
        return localizacaoService.findAll();
    }
}
