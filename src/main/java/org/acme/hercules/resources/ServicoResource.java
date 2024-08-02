package org.acme.hercules.resources;

import org.acme.hercules.entity.Servicos;
import org.acme.hercules.services.servicosServices.ServicoServiceInterface;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/servicos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicoResource {

    @Inject
    ServicoServiceInterface servicoService;

    @GET
    public List<Servicos> listAllServicos() {
        return servicoService.listAllServicos();
    }

    @GET
    @Path("/{id}")
    public Response findServicoById(@PathParam("id") Long id) {
        Servicos servico = servicoService.findServicoById(id);
        if (servico == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(servico).build();
    }

    @POST
    public Response createServico(@Valid Servicos servico) {
        try {
            servicoService.createServico(servico);
            return Response.status(Response.Status.CREATED).build();
        } catch (ConstraintViolationException e) {
            return handleValidationException(e);
        }
    }

    @PUT
    public Response updateServico(@Valid Servicos servico) {
        Servicos existingServico = servicoService.findServicoById(servico.id);
        if (existingServico == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            servicoService.updateServico(servico);
            return Response.ok().build();
        } catch (ConstraintViolationException e) {
            return handleValidationException(e);
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteServico(@PathParam("id") Long id) {
        Servicos servico = servicoService.findServicoById(id);
        if (servico == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        servicoService.deleteServico(id);
        return Response.noContent().build();
    }

    private Response handleValidationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> validationMessages = violations.stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());
        return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
    }
}
