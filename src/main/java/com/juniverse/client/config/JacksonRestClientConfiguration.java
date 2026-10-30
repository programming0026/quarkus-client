package com.juniverse.client.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

/**
 * Configuración personalizada de Jackson para el REST Client
 * Esta clase configura el ObjectMapper para el cliente REST
 * 
 * @author ajuar
 */
@Provider
public class JacksonRestClientConfiguration implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper;

    /** Configura Jackson: ignora propiedades desconocidas, escribe las fechas como texto ISO y soporta la API de fechas de Java 8+. */
    public JacksonRestClientConfiguration() {
        objectMapper = new ObjectMapper();
        
        // Configuración para manejo de propiedades desconocidas
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
        
        // Configuración para serialización
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        
        // Soporte para Java 8 Time API
        objectMapper.registerModule(new JavaTimeModule());
        
        // Configuración de naming strategy (opcional - snake_case)
        // objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        
        // Configuración para ignorar valores null en JSON
        // objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Devuelve el {@code ObjectMapper} configurado para el REST Client.
     * @param type tipo que se (de)serializa
     * @return el mapper
     */
    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }
}