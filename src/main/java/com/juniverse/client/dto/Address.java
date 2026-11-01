package com.juniverse.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author ajuar
 */
public class Address implements java.io.Serializable{

    @JsonProperty("street")
    private String street;
    
    @JsonProperty("suite")
    private String suite;
    
    @JsonProperty("city")
    private String city;
    
    @JsonProperty("zipcode")
    private String zipcode;
    
    @JsonProperty("geo")
    private Geo geo;

    /** Devuelve calle. */
    public String getStreet() {
        return street;
    }

    /** Asigna calle. */
    public void setStreet(String street) {
        this.street = street;
    }

    /** Devuelve número o apartamento. */
    public String getSuite() {
        return suite;
    }

    /** Asigna número o apartamento. */
    public void setSuite(String suite) {
        this.suite = suite;
    }

    /** Devuelve ciudad. */
    public String getCity() {
        return city;
    }

    /** Asigna ciudad. */
    public void setCity(String city) {
        this.city = city;
    }

    /** Devuelve código postal. */
    public String getZipcode() {
        return zipcode;
    }

    /** Asigna código postal. */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /** Devuelve coordenadas. */
    public Geo getGeo() {
        return geo;
    }

    /** Asigna coordenadas. */
    public void setGeo(Geo geo) {
        this.geo = geo;
    }
}
