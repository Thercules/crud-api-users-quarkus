package org.acme.hercules.resources.solicitacaoServicosResource;

import jakarta.ws.rs.core.MediaType;
import org.acme.hercules.entity.SolicitacaoServico;
import org.acme.hercules.services.solicitacaoServicosService.SolicitacaoServicoService;
import org.acme.hercules.dto.solicitacaoServicoDTO.SolicitacaoServicoRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;

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

    @GET
    public Response listAllSolicitacoes() {
        List<SolicitacaoServico> solicitacoes = solicitacaoServicoService.listAllSolicitacoes();
        return Response.ok(solicitacoes).build();
    }

    @GET
    @Path("/{id}")
    public Response findSolicitacaoById(@PathParam("id") Long id) {
        SolicitacaoServico solicitacao = solicitacaoServicoService.findSolicitacaoById(id);
        if (solicitacao != null) {
            return Response.ok(solicitacao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateSolicitacaoStatus(@PathParam("id") Long id, SolicitacaoServicoRequest request) {
        try {
            solicitacaoServicoService.updateSolicitacaoStatus(id, request.getStatus());
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSolicitacao(@PathParam("id") Long id) {
        try {
            solicitacaoServicoService.deleteSolicitacao(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
