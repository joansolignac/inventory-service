# Inventory Service

Servicio de inventario construido con Spring Boot y Gradle. Gestiona categorías,
productos, variantes y reservas de stock para que otros servicios, como uno de
órdenes, puedan comprobar y reservar inventario de forma segura.

## Características

- CRUD de categorías, productos y variantes.
- Generación de SKU de variantes a partir del SKU base del producto.
- Caché de lecturas con Redis.
- Reservas de stock atómicas e idempotencia defensiva mediante un
  `reservationId` proporcionado por el cliente.
- Respuestas de error mediante `ProblemDetail`.

## Requisitos

- JDK 25.
- Docker Desktop o servicios locales equivalentes para MySQL 8.4 y Redis 7.

## Inicio local

Desde la raíz del repositorio, inicia las dependencias:

```powershell
docker compose up -d
```

El entorno local utiliza los siguientes servicios:

| Servicio | Host | Puerto | Credenciales |
| --- | --- | --- | --- |
| MySQL | `localhost` | `3306` | `inventory_user` / `inventory_password` |
| Redis | `localhost` | `6379` | No requiere autenticación |
| API | `localhost` | `8090` | — |

Inicia la aplicación con el perfil de desarrollo:

```powershell
./gradlew.bat bootRun --args="--spring.profiles.active=dev"
```

Para detener las dependencias:

```powershell
docker compose down
```

No uses `docker compose down -v` salvo que quieras eliminar intencionalmente
los datos de MySQL.

## API

Las colecciones principales son:

| Recurso | Ruta base | Operaciones |
| --- | --- | --- |
| Categorías | `/categories` | Crear, listar, obtener, actualizar y eliminar |
| Productos | `/products` | Crear, listar, obtener, actualizar y eliminar; listar variantes por producto |
| Variantes | `/variants` | Crear, listar, obtener, actualizar, eliminar y agregar stock |
| Reservas | `/variants/{variantId}/reserve` | Crear una reserva de stock |

### Crear una reserva

```http
POST /variants/{variantId}/reserve
Content-Type: application/json

{
  "reservationId": "b7886ed0-71dc-4bfd-bbc5-0f0828597291",
  "quantity": 2
}
```

Una solicitud exitosa responde `201 Created`:

```json
{
  "reservationId": "b7886ed0-71dc-4bfd-bbc5-0f0828597291",
  "variantId": "d105e15c-abcf-41e5-9aee-c66344bec3d5",
  "quantity": 2,
  "status": "RESERVED"
}
```

El cliente —por ejemplo, el microservicio de órdenes— debe generar y conservar
el mismo `reservationId` al reintentar una operación. Si el ID ya existe, la
API responde `409 Conflict` y no descuenta stock nuevamente. Esta es una
deduplicación idempotente: el reintento no modifica el inventario, aunque no
devuelve la reserva original.

La actualización de stock es atómica: solo se descuenta cuando hay unidades
suficientes. Las solicitudes concurrentes no pueden dejar el stock en un valor
negativo. Si no hay stock suficiente, la API responde `409 Conflict`; si la
variante no existe, responde `404 Not Found`.

## Desarrollo y verificación

```powershell
./gradlew.bat compileJava
./gradlew.bat test
./gradlew.bat build
```

El perfil `dev` requiere que MySQL y Redis estén activos. Las escrituras que
modifican variantes invalidan las cachés de variante para que las lecturas no
devuelvan un stock obsoleto.

## Estructura

```text
src/main/java/com/joan/inventoryservice
├── common/       # Configuración, DTOs y manejo global de errores
└── modules/
    ├── category/    # Dominio de categorías
    ├── product/     # Productos, variantes y consultas de inventario
    └── reservation/ # Reserva atómica e idempotencia defensiva
```
