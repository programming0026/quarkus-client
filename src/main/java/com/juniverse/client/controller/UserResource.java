/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juniverse.client.controller;

/**
 *
 * @author ajuar
 */
import com.juniverse.client.dto.User;
import com.juniverse.client.service.UserService;
import com.juniverse.client.exception.RemoteServiceException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.concurrent.CompletionStage;

/** API REST propia ({@code /api/users}) que consulta y modifica usuarios en un servicio externo mediante el REST Client de Quarkus. */
@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    @Inject
    UserService userService;

    /**
     * GET /api/users - Obtiene todos los usuarios
     */
    @GET
    public Response getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return Response.ok(users).build();
        } catch (Exception e) {
            return error(e, "Error al obtener usuarios");
        }
    }

    /**
     * GET /api/users/async - Obtiene todos los usuarios de forma asíncrona
     */
    @GET
    @Path("/async")
    public CompletionStage<Response> getAllUsersAsync() {
        return userService.getAllUsersAsync()
                .thenApply(users -> Response.ok(users).build())
                .exceptionally(throwable -> error(throwable, "Error al obtener usuarios"));
    }

    /**
     * GET /api/users/{id} - Obtiene un usuario por ID
     */
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        try {
            User user = userService.getUserById(id);
            return Response.ok(user).build();
        } catch (Exception e) {
            return error(e, "Usuario no encontrado");
        }
    }

    /**
     * GET /api/users/{id}/async - Obtiene un usuario por ID de forma asíncrona
     */
    @GET
    @Path("/{id}/async")
    public CompletionStage<Response> getUserByIdAsync(@PathParam("id") Long id) {
        return userService.getUserByIdAsync(id)
                .thenApply(user -> Response.ok(user).build())
                .exceptionally(throwable -> error(throwable, "Usuario no encontrado"));
    }

    /**
     * POST /api/users - Crea un nuevo usuario
     */
    @POST
    public Response createUser(User user) {
        try {
            User createdUser = userService.createUser(user);
            return Response.status(Response.Status.CREATED)
                    .entity(createdUser)
                    .build();
        } catch (Exception e) {
            return error(e, "Error al crear usuario");
        }
    }

    /**
     * PUT /api/users/{id} - Actualiza un usuario existente
     *
     * @param id
     * @param user
     * @return
     */
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, User user) {
        try {
            User updatedUser = userService.updateUser(id, user);
            return Response.ok(updatedUser).build();
        } catch (Exception e) {
            return error(e, "Error al actualizar usuario");
        }
    }

    /**
     * DELETE /api/users/{id} - Elimina un usuario
     */
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        try {
            userService.deleteUser(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return error(e, "Error al eliminar usuario");
        }
    }

    /**
     * Construye la respuesta de error. Si el fallo viene del servicio externo se propaga su código 4xx
     * (por ejemplo 404); un 5xx o un fallo de conexión se informan como 502 (Bad Gateway).
     * El detalle técnico solo se registra en el log: al cliente se le devuelve un mensaje genérico.
     *
     * @param throwable error original (se recorre la cadena de causas)
     * @param message mensaje para el cliente
     * @return respuesta JSON {@code {"error": "..."}}
     */
    private static Response error(Throwable throwable, String message) {
        int status = Response.Status.BAD_GATEWAY.getStatusCode();
        for (Throwable cause = throwable; cause != null; cause = cause.getCause()) {
            if (cause instanceof RemoteServiceException remote) {
                if (remote.getStatus() >= 400 && remote.getStatus() < 500) {
                    status = remote.getStatus();
                }
                break;
            }
        }
        LOGGER.warn("{} (HTTP {}): {}", message, status, throwable.getMessage());
        return Response.status(status).entity(Map.of("error", message)).build();
    }
}
