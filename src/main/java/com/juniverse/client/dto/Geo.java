package com.juniverse.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO para representar coordenadas geográficas
 * 
 * @author Java Universe
 */
public class Geo implements java.io.Serializable {

    @JsonProperty("lat")
    private String lat;
    
    @JsonProperty("lng")
    private String lng;

    /** Crea unas coordenadas vacías. */
    public Geo() {
    }

    /** Devuelve latitud. */
    public String getLat() {
        return lat;
    }

    /** Asigna latitud. */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /** Devuelve longitud. */
    public String getLng() {
        return lng;
    }

    /** Asigna longitud. */
    public void setLng(String lng) {
        this.lng = lng;
    }
}