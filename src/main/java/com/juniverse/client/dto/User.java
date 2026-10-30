package com.juniverse.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 *
 * @author ajuar
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements java.io.Serializable{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private Address address;

    /** Crea un usuario vacío (necesario para la deserialización JSON). */
    public User() {
    }

    /**
     * Crea un usuario.
     * @param id identificador
     * @param name nombre
     * @param username nombre de usuario
     * @param email correo electrónico
     */
    public User(Long id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;

    }

    /** Devuelve identificador. */
    public Long getId() {
        return id;
    }

    /** Asigna identificador. */
    public void setId(Long id) {
        this.id = id;
    }

    /** Devuelve nombre. */
    public String getName() {
        return name;
    }

    /** Asigna nombre. */
    public void setName(String name) {
        this.name = name;
    }

    /** Devuelve nombre de usuario. */
    public String getUsername() {
        return username;
    }

    /** Asigna nombre de usuario. */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Devuelve correo electrónico. */
    public String getEmail() {
        return email;
    }

    /** Asigna correo electrónico. */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Devuelve dirección. */
    public Address getAddress() {
        return address;
    }

    /** Asigna dirección. */
    public void setAddress(Address address) {
        this.address = address;
    }

}
