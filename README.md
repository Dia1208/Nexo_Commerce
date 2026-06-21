# NexoCommerce - Sistema E-Commerce con Microservicios

## Descripción del proyecto

NexoCommerce es un sistema de comercio electrónico desarrollado con arquitectura de microservicios usando Spring Boot.

El proyecto permite gestionar autenticación, usuarios, productos, pedidos, pagos, notificaciones y reportes. La comunicación principal se realiza a través de un API Gateway, que funciona como punto único de entrada para las peticiones del cliente.

El sistema está diseñado separando responsabilidades por microservicio, permitiendo que cada módulo tenga su propia lógica, su propia base de datos y sus propios endpoints.

---

## Integrantes

- Diego Gonzalez Ballesteros

---

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Cloud Gateway
- MySQL
- Lombok
- Swagger / OpenAPI
- Maven
- Git / GitHub
- Postman
- IntelliJ IDEA

---

## Arquitectura del sistema

El sistema está dividido en los siguientes microservicios:

| Microservicio | Puerto | Descripción |
|---|---:|---|
| servicio-gateway | 8080 | Punto único de entrada al sistema |
| servicio-autenticacion | 8081 | Registro, login y generación de JWT |
| servicio-usuarios | 8082 | Gestión de usuarios |
| servicio-productos | 8083 | Gestión de productos |
| servicio-pedidos | 8084 | Gestión de pedidos |
| servicio-pagos | 8085 | Gestión de pagos |
| servicio-notificaciones | 8086 | Gestión de notificaciones |
| servicio-reportes | 8087 | Gestión de reportes |

---

## Base de datos

Cada microservicio utiliza su propia base de datos en MySQL.

```sql
CREATE DATABASE IF NOT EXISTS nexocommerce_autenticacion_db;
CREATE DATABASE IF NOT EXISTS nexocommerce_usuarios_db;
CREATE DATABASE IF NOT EXISTS nexocommerce_productos_db;
CREATE DATABASE IF NOT EXISTS nexocommerce_pedidos_db;
CREATE DATABASE IF NOT EXISTS nexocommerce_pagos_db;
CREATE DATABASE IF NOT EXISTS nexocommerce_notificaciones_db;
CREATE DATABASE IF NOT EXISTS nexocommerce_reportes_db;
```

Relación entre microservicio y base de datos:

| Microservicio | Base de datos |
|---|---|
| servicio-autenticacion | nexocommerce_autenticacion_db |
| servicio-usuarios | nexocommerce_usuarios_db |
| servicio-productos | nexocommerce_productos_db |
| servicio-pedidos | nexocommerce_pedidos_db |
| servicio-pagos | nexocommerce_pagos_db |
| servicio-notificaciones | nexocommerce_notificaciones_db |
| servicio-reportes | nexocommerce_reportes_db |

---

## Configuración general

Cada microservicio contiene su propio archivo:

```txt
src/main/resources/application.properties
```

Ejemplo de configuración:

```properties
server.port=8082

spring.application.name=servicio-usuarios

spring.datasource.url=jdbc:mysql://localhost:3306/nexocommerce_usuarios_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.open-in-view=false

springdoc.swagger-ui.path=/swagger-ui.html

management.endpoints.web.exposure.include=health
management.endpoint.health.show-details=always
```

---

## API Gateway

El microservicio `servicio-gateway` se ejecuta en el puerto `8080` y redirecciona las peticiones a los demás servicios.

Rutas configuradas:

| Ruta Gateway | Microservicio destino |
|---|---|
| `/api/auth/**` | servicio-autenticacion |
| `/api/usuarios/**` | servicio-usuarios |
| `/api/productos/**` | servicio-productos |
| `/api/pedidos/**` | servicio-pedidos |
| `/api/pagos/**` | servicio-pagos |
| `/api/notificaciones/**` | servicio-notificaciones |
| `/api/reportes/**` | servicio-reportes |

---

## Seguridad

El sistema utiliza JWT para proteger las rutas privadas.

Flujo de seguridad:

1. El usuario se registra o inicia sesión en `servicio-autenticacion`.
2. El servicio de autenticación genera un token JWT.
3. El cliente envía el token en cada petición protegida.
4. El Gateway valida el token antes de permitir el acceso a los microservicios.

Formato del header:

```txt
Authorization: Bearer TOKEN_GENERADO
```

Rutas públicas:

```txt
/api/auth/register
/api/auth/login
/api/auth/test
/api/gateway/health
/actuator/health
```

---

## Ejecución del proyecto

Para ejecutar el sistema completo, iniciar los microservicios en este orden:

```txt
1. ServicioAutenticacionApplication  → puerto 8081
2. ServicioUsuariosApplication       → puerto 8082
3. ServicioProductosApplication      → puerto 8083
4. ServicioPedidosApplication        → puerto 8084
5. ServicioPagosApplication          → puerto 8085
6. ServicioNotificacionesApplication → puerto 8086
7. ServicioReportesApplication       → puerto 8087
8. ServicioGatewayApplication        → puerto 8080
```

El Gateway se debe ejecutar al final porque es el punto de entrada que redirecciona hacia los demás microservicios.

---

## Documentación Swagger

Cada microservicio cuenta con Swagger UI para probar sus endpoints.

| Microservicio | URL |
|---|---|
| Autenticación | `http://localhost:8081/swagger-ui.html` |
| Usuarios | `http://localhost:8082/swagger-ui.html` |
| Productos | `http://localhost:8083/swagger-ui.html` |
| Pedidos | `http://localhost:8084/swagger-ui.html` |
| Pagos | `http://localhost:8085/swagger-ui.html` |
| Notificaciones | `http://localhost:8086/swagger-ui.html` |
| Reportes | `http://localhost:8087/swagger-ui.html` |

---

## Endpoints principales

Todas las rutas siguientes se pueden probar desde el Gateway usando el puerto `8080`.

---

## Autenticación

### Registrar usuario

```http
POST http://localhost:8080/api/auth/register
```

Body:

```json
{
  "nombre": "Juan Prueba",
  "correo": "juanprueba@test.com",
  "password": "123456"
}
```

Respuesta esperada:

```json
{
  "token": "TOKEN_GENERADO",
  "tipo": "Bearer",
  "mensaje": "Usuario registrado correctamente"
}
```

### Login

```http
POST http://localhost:8080/api/auth/login
```

Body:

```json
{
  "correo": "juanprueba@test.com",
  "password": "123456"
}
```

Respuesta esperada:

```json
{
  "token": "TOKEN_GENERADO",
  "tipo": "Bearer",
  "mensaje": "Login correcto"
}
```

---

## Usuarios

### Crear usuario

```http
POST http://localhost:8080/api/usuarios/crear
```

Body:

```json
{
  "nombre": "Juan Prueba",
  "correo": "juanprueba@test.com",
  "telefono": "123456789",
  "direccion": "Calle 123",
  "rol": "CLIENTE"
}
```

### Listar usuarios

```http
GET http://localhost:8080/api/usuarios/listar
```

### Buscar usuario por ID

```http
GET http://localhost:8080/api/usuarios/1
```

### Buscar usuario por correo

```http
GET http://localhost:8080/api/usuarios/correo/juanprueba@test.com
```

### Actualizar usuario

```http
PUT http://localhost:8080/api/usuarios/1
```

Body:

```json
{
  "nombre": "Juan Actualizado",
  "correo": "juanactualizado@test.com",
  "telefono": "987654321",
  "direccion": "Nueva dirección",
  "rol": "CLIENTE"
}
```

### Eliminar usuario

```http
DELETE http://localhost:8080/api/usuarios/1
```

---

## Productos

### Crear producto

```http
POST http://localhost:8080/api/productos
```

Body:

```json
{
  "nombre": "Teclado Mecánico",
  "descripcion": "Teclado gamer RGB",
  "precio": 45990,
  "stock": 10,
  "categoria": "Tecnología"
}
```

### Listar productos

```http
GET http://localhost:8080/api/productos
```

### Buscar producto por ID

```http
GET http://localhost:8080/api/productos/1
```

### Actualizar producto

```http
PUT http://localhost:8080/api/productos/1
```

Body:

```json
{
  "nombre": "Mouse Gamer",
  "descripcion": "Mouse gamer RGB",
  "precio": 25990,
  "stock": 15,
  "categoria": "Tecnología"
}
```

### Eliminar producto

```http
DELETE http://localhost:8080/api/productos/1
```

---

## Pedidos

### Crear pedido

```http
POST http://localhost:8080/api/pedidos
```

Body:

```json
{
  "correoUsuario": "juanprueba@test.com",
  "productoId": 1,
  "nombreProducto": "Teclado Mecánico",
  "cantidad": 2,
  "precioUnitario": 45990
}
```

Respuesta esperada:

```json
{
  "id": 1,
  "correoUsuario": "juanprueba@test.com",
  "productoId": 1,
  "nombreProducto": "Teclado Mecánico",
  "cantidad": 2,
  "precioUnitario": 45990,
  "total": 91980,
  "estado": "PENDIENTE",
  "fechaCreacion": "2026-06-21T00:00:00"
}
```

### Listar pedidos

```http
GET http://localhost:8080/api/pedidos
```

### Buscar pedido por ID

```http
GET http://localhost:8080/api/pedidos/1
```

### Listar pedidos por usuario

```http
GET http://localhost:8080/api/pedidos/usuario/juanprueba@test.com
```

### Actualizar estado del pedido

```http
PUT http://localhost:8080/api/pedidos/1/estado
```

Body:

```json
{
  "estado": "ENVIADO"
}
```

### Cancelar pedido

```http
PUT http://localhost:8080/api/pedidos/1/cancelar
```

---

## Pagos

### Crear pago

```http
POST http://localhost:8080/api/pagos
```

Body:

```json
{
  "pedidoId": 1,
  "correoUsuario": "juanprueba@test.com",
  "monto": 91980,
  "metodoPago": "TARJETA"
}
```

### Listar pagos

```http
GET http://localhost:8080/api/pagos
```

### Buscar pago por ID

```http
GET http://localhost:8080/api/pagos/1
```

### Listar pagos por pedido

```http
GET http://localhost:8080/api/pagos/pedido/1
```

### Listar pagos por usuario

```http
GET http://localhost:8080/api/pagos/usuario/juanprueba@test.com
```

### Aprobar pago

```http
PUT http://localhost:8080/api/pagos/1/aprobar
```

### Rechazar pago

```http
PUT http://localhost:8080/api/pagos/1/rechazar
```

---

## Notificaciones

### Crear notificación

```http
POST http://localhost:8080/api/notificaciones
```

Body:

```json
{
  "correoUsuario": "juanprueba@test.com",
  "titulo": "Pedido enviado",
  "mensaje": "Tu pedido fue enviado correctamente",
  "tipo": "PEDIDO"
}
```

### Listar notificaciones

```http
GET http://localhost:8080/api/notificaciones
```

### Buscar notificación por ID

```http
GET http://localhost:8080/api/notificaciones/1
```

### Listar notificaciones por usuario

```http
GET http://localhost:8080/api/notificaciones/usuario/juanprueba@test.com
```

### Listar notificaciones por tipo

```http
GET http://localhost:8080/api/notificaciones/tipo/PEDIDO
```

### Marcar como enviada

```http
PUT http://localhost:8080/api/notificaciones/1/enviar
```

### Marcar como fallida

```http
PUT http://localhost:8080/api/notificaciones/1/fallar
```

---

## Reportes

### Crear reporte

```http
POST http://localhost:8080/api/reportes
```

Body:

```json
{
  "nombre": "Reporte general de ventas",
  "tipo": "VENTAS",
  "cantidadPedidos": 1,
  "cantidadProductos": 1,
  "totalVentas": 91980
}
```

### Listar reportes

```http
GET http://localhost:8080/api/reportes
```

### Buscar reporte por ID

```http
GET http://localhost:8080/api/reportes/1
```

### Listar reportes por tipo

```http
GET http://localhost:8080/api/reportes/tipo/VENTAS
```

### Eliminar reporte

```http
DELETE http://localhost:8080/api/reportes/1
```

---

## Flujo de prueba recomendado

Para probar el sistema completo se recomienda seguir este flujo:

```txt
1. Registrar usuario en autenticación.
2. Iniciar sesión y copiar el token JWT.
3. Crear usuario en el microservicio de usuarios.
4. Crear producto.
5. Crear pedido usando el ID del producto.
6. Actualizar el estado del pedido.
7. Crear pago asociado al pedido.
8. Aprobar pago.
9. Crear notificación.
10. Marcar notificación como enviada.
11. Crear reporte.
12. Listar reportes por tipo.
```

Todas las peticiones protegidas deben enviarse con el header:

```txt
Authorization: Bearer TOKEN_GENERADO
```

---

## Manejo de errores

El sistema cuenta con manejo global de excepciones en los microservicios.

Cuando se envía un JSON vacío, por ejemplo:

```json
{}
```

El sistema responde con los campos requeridos:

```json
{
  "mensaje": "Error de validación",
  "estado": 400,
  "camposRequeridos": {
    "nombre": "El nombre es obligatorio",
    "correo": "El correo es obligatorio"
  }
}
```

Cuando se busca un recurso inexistente:

```json
{
  "mensaje": "Recurso no encontrado con id: 99",
  "estado": 404
}
```

Cuando el JSON tiene formato incorrecto:

```json
{
  "mensaje": "El JSON enviado no es válido. Revisa los nombres de los campos y los valores permitidos.",
  "estado": 400
}
```

---

## Pruebas unitarias

El proyecto incluye pruebas unitarias para los servicios principales:

```txt
AuthServiceTest
UsuarioServiceTest
ProductoServiceTest
PedidoServiceTest
PagoServiceTest
NotificacionServiceTest
ReporteServiceTest
GatewayControllerTest
```

Estas pruebas validan operaciones como:

- Registro y login.
- Creación y consulta de usuarios.
- CRUD de productos.
- Creación y actualización de pedidos.
- Creación, aprobación y rechazo de pagos.
- Creación y actualización de notificaciones.
- Creación, consulta y eliminación de reportes.
- Verificación básica del Gateway.

---

## Comandos Git utilizados

```bash
git status
git add .
git commit -m "mensaje del commit"
git push
```

---

## Estado final del proyecto

El sistema queda organizado como una arquitectura de microservicios funcional, con:

- Separación por responsabilidades.
- Base de datos independiente por microservicio.
- Gateway como punto único de entrada.
- Autenticación mediante JWT.
- Documentación Swagger.
- Validaciones de datos.
- Manejo de errores personalizado.
- Pruebas unitarias.
- Control de versiones con Git y GitHub.

---

## Observaciones

Para ejecutar correctamente el sistema se debe tener MySQL iniciado y las bases de datos creadas.

También se recomienda iniciar primero los microservicios internos y al final el Gateway.
