package org.acme.hercules.resources;

import jakarta.ws.rs.core.MediaType;
import org.acme.hercules.services.solicitacaoServicosService.SolicitacaoServicoService;
import org.acme.hercules.dto.SolicitacaoServicoRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/solicitacoesServicos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SolicitacaoServicoResource {

    @Inject
    SolicitacaoServicoService solicitacaoServicoService;

    @POST
    public Response createSolicitacaoServico(SolicitacaoServicoRequest request) {
        try {
            solicitacaoServicoService.createSolicitacaoServico(request.getUserId(), request.getServicoId(), request.getStatus());
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
