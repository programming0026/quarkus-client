package com.juniverse.client.exception;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception mapper para manejar errores HTTP del REST client
 * 
 * @author ajuar
 */
@Provider
public class RestClientExceptionMapper implements ResponseExceptionMapper<RuntimeException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientExceptionMapper.class);

    /**
     * Convierte una respuesta HTTP de error del servicio externo en una {@link RemoteServiceException} que conserva el código de estado.
     * @param response respuesta con estado 4xx o 5xx
     * @return la excepción
     */
    @Override
    public RuntimeException toThrowable(Response response) {
        int status = response.getStatus();
        String statusText = response.getStatusInfo().getReasonPhrase();
        
        // Intentar leer el cuerpo de la respuesta para más detalles
        String responseBody = "";
        try {
            if (response.hasEntity()) {
                responseBody = response.readEntity(String.class);
            }
        } catch (Exception e) {
            LOGGER.warn("No se pudo leer el cuerpo de la respuesta de error: {}", e.getMessage());
        }

        String message = switch (status) {
            case 400 -> "Solicitud incorrecta al servicio externo";
            case 401 -> "No autorizado para acceder al servicio externo";
            case 403 -> "Acceso prohibido al servicio externo";
            case 404 -> "Recurso no encontrado en el servicio externo";
            case 500 -> "Error interno del servicio externo";
            case 502 -> "Bad Gateway del servicio externo";
            case 503 -> "Servicio externo no disponible";
            case 504 -> "Timeout del servicio externo";
            default -> "Error en la llamada al servicio externo";
        };

        String fullMessage = String.format("%s (Status: %d - %s)", message, status, statusText);
        if (!responseBody.isEmpty()) {
            fullMessage += ". Detalles: " + responseBody;
        }

        LOGGER.error("Error en REST Client - Status: {} ({}), URL: {}, Response: {}", 
                    status, statusText, 
                    response.getLocation() != null ? response.getLocation().toString() : "N/A",
                    responseBody.isEmpty() ? "Sin contenido" : responseBody);

        return new RemoteServiceException(fullMessage, status);
    }

    /**
     * Solo se encarga de los códigos de error (400 o superior).
     * @param status código HTTP
     * @param headers cabeceras de la respuesta
     * @return {@code true} si es un error
     */
    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        // Solo manejar códigos de error HTTP (4xx y 5xx)
        return status >= 400;
    }

}
