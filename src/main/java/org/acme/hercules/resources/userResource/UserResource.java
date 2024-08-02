package org.acme.hercules.resources.userResource;

import org.acme.hercules.entity.User;
import org.acme.hercules.services.userService.UserServiceInterface;

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

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserServiceInterface userService;

    @GET
    public List<User> listAllUsers() {
        return userService.listAllUsers();
    }

    @GET
    @Path("/{id}")
    public Response findUserById(@PathParam("id") Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @POST
    public Response createUser(@Valid User user) {
        user.cpf = user.cpf.replaceAll("[^\\d]", "");  // Remove non-digit characters
        try {
            userService.createUser(user);
            return Response.status(Response.Status.CREATED).build();
        } catch (ConstraintViolationException e) {
            return handleValidationException(e);
        }
    }

    @PUT
    public Response updateUser(@Valid User user) {
        User existingUser = userService.findUserById(user.id);
        if (existingUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            userService.updateUser(user);
            return Response.ok().build();
        } catch (ConstraintViolationException e) {
            return handleValidationException(e);
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        userService.deleteUser(id);
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
