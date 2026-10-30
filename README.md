# Quarkus REST Client

Ejemplo de cómo consumir una API REST externa desde Quarkus con **REST Client reactivo**, exponiendo una API propia que reenvía las operaciones (por defecto contra JSONPlaceholder).

## Características

- Cliente declarativo (`@RegisterRestClient`) con llamadas síncronas y asíncronas (`CompletionStage`).
- API propia en `/api/users` con CRUD completo.
- Traducción de errores del servicio externo: un 4xx se propaga (por ejemplo 404) y un 5xx o un fallo de red se informan como 502.
- Tiempos de espera de conexión y lectura configurables.
- Configuración de Jackson (propiedades desconocidas, fechas ISO).
- Dockerfile y Docker Compose con Java 21.

## Requisitos

- JDK 21 y Maven 3.9 o superior.

## Compilar y ejecutar

```bash
mvn quarkus:dev            # modo desarrollo con recarga en caliente

# o empaquetado
mvn clean package
java -jar target/quarkus-client-1.0-SNAPSHOT-runner.jar
```

## Endpoints

| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/users` | Lista los usuarios |
| `GET` | `/api/users/async` | Lista los usuarios (asíncrono) |
| `GET` | `/api/users/{id}` | Obtiene un usuario |
| `GET` | `/api/users/{id}/async` | Obtiene un usuario (asíncrono) |
| `POST` | `/api/users` | Crea un usuario |
| `PUT` | `/api/users/{id}` | Actualiza un usuario |
| `DELETE` | `/api/users/{id}` | Elimina un usuario |

```bash
curl http://localhost:8080/api/users/1
```

## Configuración

| Variable | Descripción | Valor por defecto |
|----------|-------------|-------------------|
| `API_URL` | URL del servicio externo (perfil `prod`) | `https://jsonplaceholder.typicode.com` |
| `CONNECT_TIMEOUT` | Espera de conexión en ms | `3000` |
| `READ_TIMEOUT` | Espera de lectura en ms | `5000` |

## Docker

```bash
docker compose up --build
```

## Notas

- JSONPlaceholder es una API de pruebas: acepta las operaciones de escritura pero no guarda los cambios.

## Licencia

Código de ejemplo de uso libre con fines educativos.
