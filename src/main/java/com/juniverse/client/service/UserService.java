package com.juniverse.client.service;

import com.juniverse.client.UserRestClient;
import com.juniverse.client.dto.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
/**
 *
 * @author ajuar
 */
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Inject
    @RestClient
    UserRestClient userRestClient;

    /**
     * Obtiene todos los usuarios
     * @return 
     */
    public List<User> getAllUsers() {
        try {
            LOGGER.info("Obteniendo todos los usuarios...");
            List<User> users = userRestClient.getAllUsers();
            LOGGER.info("Se obtuvieron {} usuarios", users.size());
            return users;
        } catch (Exception e) {
            LOGGER.error("Error al obtener usuarios: {}", e.getMessage());
            throw new RuntimeException("Error al obtener usuarios", e);
        }
    }

    /**
     * Obtiene todos los usuarios de forma asíncrona
     * @return 
     */
    public CompletionStage<List<User>> getAllUsersAsync() {
        LOGGER.info("Obteniendo todos los usuarios de forma asíncrona...");
        return userRestClient.getAllUsersAsync()
                .thenApply(users -> {
                    LOGGER.info("Se obtuvieron {} usuarios de forma asíncrona", users.size());
                    return users;
                })
                .exceptionally(throwable -> {
                    LOGGER.error("Error al obtener usuarios de forma asíncrona: {}", throwable.getMessage());
                    throw new RuntimeException("Error al obtener usuarios", throwable);
                });
    }

    /**
     * Obtiene un usuario por ID
     * @param id
     * @return 
     */
    public User getUserById(Long id) {
        try {
            LOGGER.info("Obteniendo usuario con ID: {}", id);
            User user = userRestClient.getUserById(id);
            LOGGER.info("Usuario obtenido: {}", user.getName());
            return user;
        } catch (Exception e) {
            LOGGER.error("Error al obtener usuario con ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error al obtener usuario", e);
        }
    }

    /**
     * Obtiene un usuario por ID de forma asíncrona
     * @param id
     * @return 
     */
    public CompletionStage<User> getUserByIdAsync(Long id) {
        LOGGER.info("Obteniendo usuario con ID {} de forma asíncrona...", id);
        return userRestClient.getUserByIdAsync(id)
                .thenApply(user -> {
                    LOGGER.info("Usuario obtenido de forma asíncrona: {}", user.getName());
                    return user;
                })
                .exceptionally(throwable -> {
                    LOGGER.error("Error al obtener usuario con ID {} de forma asíncrona: {}", id, throwable.getMessage());
                    throw new RuntimeException("Error al obtener usuario", throwable);
                });
    }

    /**
     * Crea un nuevo usuario
     * @param user
     * @return 
     */
    public User createUser(User user) {
        try {
            LOGGER.info("Creando nuevo usuario: {}", user.getName());
            User createdUser = userRestClient.createUser(user);
            LOGGER.info("Usuario creado con ID: {}", createdUser.getId());
            return createdUser;
        } catch (Exception e) {
            LOGGER.error("Error al crear usuario: {}", e.getMessage());
            throw new RuntimeException("Error al crear usuario", e);
        }
    }

    /**
     * Actualiza un usuario existente
     * @param id
     * @param user
     * @return 
     */
    public User updateUser(Long id, User user) {
        try {
            LOGGER.info("Actualizando usuario con ID: {}", id);
            User updatedUser = userRestClient.updateUser(id, user);
            LOGGER.info("Usuario actualizado: {}", updatedUser.getName());
            return updatedUser;
        } catch (Exception e) {
            LOGGER.error("Error al actualizar usuario con ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error al actualizar usuario", e);
        }
    }

    /**
     * Elimina un usuario
     * @param id
     */
    public void deleteUser(Long id) {
        try {
            LOGGER.info("Eliminando usuario con ID: {}", id);
            userRestClient.deleteUser(id);
            LOGGER.info("Usuario eliminado exitosamente");
        } catch (Exception e) {
            LOGGER.error("Error al eliminar usuario con ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error al eliminar usuario", e);
        }
    }
}
