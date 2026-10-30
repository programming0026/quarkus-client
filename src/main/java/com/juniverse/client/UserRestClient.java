/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.juniverse.client;

import com.juniverse.client.dto.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 *
 * @author ajuar
 */
@RegisterRestClient(configKey = "user-api")
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UserRestClient {

    /**
     * Get all users synchronous
     * @return List
     */
    @GET
    List<User> getAllUsers();

    /**
     * Get all users asynchronous
     * @return CompletionStage<List<User>>
     */
    @GET
    CompletionStage<List<User>> getAllUsersAsync();

    /**
     * Get a user by ID
     * @param id The id
     * @return User
     */
    @GET
    @Path("/{id}")
    User getUserById(@PathParam("id") Long id);

    /**
     * Obtain a user by Asynchronous ID
     * @param id The id
     * @return CompletionStage<User>
     */
    @GET
    @Path("/{id}")
    CompletionStage<User> getUserByIdAsync(@PathParam("id") Long id);

    /**
     * Create a new user
     * @param user The user
     * @return User
     */
    @POST
    User createUser(User user);

    /**
     * Update an existing user
     * @param id The id
     * @param user The user
     * @return User
     */
    @PUT
    @Path("/{id}")
    User updateUser(@PathParam("id") Long id, User user);

    /**
     * Eliminate a user
     * @param id The id
     */
    @DELETE
    @Path("/{id}")
    void deleteUser(@PathParam("id") Long id);
}
