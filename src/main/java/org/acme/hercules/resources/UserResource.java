package org.acme.hercules.resources;

import org.acme.hercules.entity.User;
import org.acme.hercules.services.UserServiceInterface;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

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
    public Response createUser(User user) {
        userService.createUser(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response updateUser(User user) {
        User existingUser = userService.findUserById(user.id);
        if (existingUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        userService.updateUser(user);
        return Response.ok().build();
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
}
