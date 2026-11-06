package com.juniverse.client;

import com.juniverse.client.dto.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 *
 * @author Java Universe
 */
@RegisterRestClient(configKey = "user-api")
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UserRestClient {

    /**
     * Obtiene todos los usuarios de forma síncrona
     * @return List
     */
    @GET
    List<User> getAllUsers();

    /**
     * Obtiene todos los usuarios de forma asíncrona
     * @return CompletionStage<List<User>>
     */
    @GET
    CompletionStage<List<User>> getAllUsersAsync();

    /**
     * Obtiene un usuario por identificador
     * @param id identificador
     * @return User
     */
    @GET
    @Path("/{id}")
    User getUserById(@PathParam("id") Long id);

    /**
     * Obtiene un usuario por identificador de forma asíncrona
     * @param id identificador
     * @return CompletionStage<User>
     */
    @GET
    @Path("/{id}")
    CompletionStage<User> getUserByIdAsync(@PathParam("id") Long id);

    /**
     * Crea un usuario nuevo
     * @param user usuario
     * @return User
     */
    @POST
    User createUser(User user);

    /**
     * Actualiza un usuario existente
     * @param id identificador
     * @param user usuario
     * @return User
     */
    @PUT
    @Path("/{id}")
    User updateUser(@PathParam("id") Long id, User user);

    /**
     * Elimina un usuario
     * @param id identificador
     */
    @DELETE
    @Path("/{id}")
    void deleteUser(@PathParam("id") Long id);
}
