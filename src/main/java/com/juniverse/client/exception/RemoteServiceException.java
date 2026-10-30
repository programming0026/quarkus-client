package com.juniverse.client.exception;

/**
 * Error devuelto por el servicio externo (respuesta HTTP 4xx o 5xx). Conserva el código de estado
 * para que la API propia pueda responder con uno coherente.
 */
public class RemoteServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int status;

    /**
     * Crea la excepción.
     *
     * @param message descripción del error (para el log; no se muestra tal cual al cliente)
     * @param status código HTTP que devolvió el servicio externo
     */
    public RemoteServiceException(String message, int status) {
        super(message);
        this.status = status;
    }

    /**
     * Código HTTP que devolvió el servicio externo.
     *
     * @return código de estado, por ejemplo 404
     */
    public int getStatus() {
        return status;
    }
}
