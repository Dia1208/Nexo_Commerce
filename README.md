# NexoCommerce - Sistema E-Commerce con Microservicios

## Descripción del proyecto

NexoCommerce es una plataforma backend de comercio electrónico desarrollada con arquitectura de microservicios. El sistema permite gestionar autenticación, usuarios, productos, pedidos, pagos, notificaciones, reportes y el flujo de checkout de forma separada, organizada y escalable.

El proyecto fue desarrollado con Spring Boot y aplica una arquitectura distribuida donde cada microservicio tiene una responsabilidad específica. La comunicación entre servicios se realiza mediante endpoints REST y, en el flujo principal de compra, mediante WebClient.

El sistema cuenta con un API Gateway como punto único de entrada, documentación Swagger/OpenAPI, persistencia con MySQL, pruebas unitarias con JUnit y Mockito, y despliegue local mediante Docker Compose.

---

## Integrantes

- Diego Gonzalez Ballesteros

---

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring WebFlux
- Spring Data JPA
- Hibernate
- MySQL
- Spring Security
- JWT
- Spring Cloud Gateway
- WebClient
- Lombok
- Swagger / OpenAPI
- JUnit 5
- Mockito
- Maven
- Docker
- Docker Compose
- Git / GitHub
- Postman
- IntelliJ IDEA

---

## Arquitectura del sistema

El sistema está dividido en microservicios independientes. Cada microservicio se organiza siguiendo el patrón CSR:

- Controller: expone los endpoints REST.
- Service: contiene la lógica de negocio.
- Repository: comunica con la base de datos.
- Model / Entity: representa las entidades del dominio.
- DTO: transporta datos entre capas o entre microservicios.

El API Gateway funciona como punto de entrada centralizado para redirigir las solicitudes hacia los microservicios correspondientes.

---

## Microservicios implementados

| Microservicio | Puerto | Responsabilidad |
|---|---:|---|
| servicio-gateway | 8080 | Punto único de entrada al sistema y enrutamiento hacia los microservicios |
| servicio-autenticacion | 8081 | Registro, login y generación de token JWT |
| servicio-usuarios | 8082 | Gestión CRUD de usuarios |
| servicio-productos | 8083 | Gestión CRUD de productos |
| servicio-pedidos | 8084 | Gestión de pedidos |
| servicio-pagos | 8085 | Gestión de pagos |
| servicio-notificaciones | 8086 | Gestión de notificaciones |
| servicio-reportes | 8087 | Gestión de reportes |
| servicio-checkout | 8088 | Orquestación del flujo de compra |

---

## Microservicio orquestador: servicio-checkout

El microservicio `servicio-checkout` actúa como orquestador del flujo de compra.

Antes, `servicio-pedidos` consultaba directamente a `servicio-productos`. Luego se ajustó la arquitectura para separar mejor las responsabilidades.

Ahora el flujo funciona así:

1. `servicio-checkout` recibe el correo del usuario, el ID del producto y la cantidad.
2. `servicio-checkout` consulta `servicio-productos` usando WebClient.
3. Valida que el producto exista y que tenga stock suficiente.
4. Calcula el total del pedido.
5. Envía el pedido armado a `servicio-pedidos`.
6. `servicio-pedidos` guarda y gestiona el pedido.

Esto permite que:

- `servicio-productos` se encargue de los productos.
- `servicio-checkout` coordine el flujo de compra.
- `servicio-pedidos` se encargue solo de guardar y administrar pedidos.

---

## Base de datos

El proyecto utiliza MySQL. Cada microservicio con persistencia utiliza su propia base de datos.

El microservicio `servicio-checkout` no tiene base de datos propia porque su función es orquestar el flujo entre productos y pedidos.

Bases de datos utilizadas:

```sql
CREATE DATABASE IF NOT EXISTS nexocommerce_autenticacion;
CREATE DATABASE IF NOT EXISTS nexocommerce_usuarios;
CREATE DATABASE IF NOT EXISTS nexocommerce_productos;
CREATE DATABASE IF NOT EXISTS nexocommerce_pedidos;
CREATE DATABASE IF NOT EXISTS nexocommerce_pagos;
CREATE DATABASE IF NOT EXISTS nexocommerce_notificaciones;
CREATE DATABASE IF NOT EXISTS nexocommerce_reportes;
```

Relación entre microservicio y base de datos:

| Microservicio | Base de datos |
|---|---|
| servicio-autenticacion | nexocommerce_autenticacion |
| servicio-usuarios | nexocommerce_usuarios |
| servicio-productos | nexocommerce_productos |
| servicio-pedidos | nexocommerce_pedidos |
| servicio-pagos | nexocommerce_pagos |
| servicio-notificaciones | nexocommerce_notificaciones |
| servicio-reportes | nexocommerce_reportes |
| servicio-checkout | No utiliza base de datos propia |

---

## Configuración general

Cada microservicio contiene su propia configuración en:

```txt
src/main/resources/application.properties
```

o:

```txt
src/main/resources/application.yml
```

El `servicio-gateway` utiliza `application.yml` para definir:

- Puerto del Gateway.
- Rutas hacia los microservicios.
- Rutas de Swagger centralizado.
- Configuración JWT.
- Configuración de Actuator.
- Variables de entorno para ejecución con Docker Compose.

El proyecto padre contiene:

```txt
docker-compose.yml
```

Este archivo permite levantar todos los microservicios en contenedores Docker.

---

## API Gateway

El microservicio `servicio-gateway` se ejecuta en el puerto `8080`.

Base URL del Gateway:

```txt
http://localhost:8080
```

Rutas principales configuradas:

| Ruta Gateway | Microservicio destino |
|---|---|
| `/api/auth/**` | servicio-autenticacion |
| `/api/usuarios/**` | servicio-usuarios |
| `/api/productos/**` | servicio-productos |
| `/api/pedidos/**` | servicio-pedidos |
| `/api/pagos/**` | servicio-pagos |
| `/api/notificaciones/**` | servicio-notificaciones |
| `/api/reportes/**` | servicio-reportes |
| `/api/checkout/**` | servicio-checkout |

Para verificar que el Gateway está activo:

```txt
http://localhost:8080/actuator/health
```

Respuesta esperada:

```json
{
  "status": "UP"
}
```

---

## Seguridad

El sistema utiliza JWT para proteger rutas privadas.

Flujo de seguridad:

1. El usuario se registra o inicia sesión en `servicio-autenticacion`.
2. El servicio de autenticación genera un token JWT.
3. El cliente envía el token en cada petición protegida.
4. El Gateway valida el token antes de permitir el acceso a los microservicios.

Formato del header:

```txt
Authorization: Bearer TOKEN_GENERADO
```

Rutas públicas principales:

```txt
/api/auth/**
/actuator/health
/swagger-ui/**
/swagger-ui.html
/v3/api-docs/**
```

---

## Swagger / OpenAPI

El proyecto cuenta con documentación Swagger/OpenAPI centralizada desde el API Gateway.

URL principal:

```txt
http://localhost:8080/swagger-ui.html
```

También puede abrirse como:

```txt
http://localhost:8080/swagger-ui/index.html
```

Desde Swagger se puede seleccionar la documentación de:

- Autenticación
- Usuarios
- Productos
- Pedidos
- Pagos
- Notificaciones
- Reportes
- Checkout

Documentación OpenAPI por microservicio mediante Gateway:

| Microservicio | URL Gateway |
|---|---|
| Autenticación | `http://localhost:8080/v3/api-docs/autenticacion` |
| Usuarios | `http://localhost:8080/v3/api-docs/usuarios` |
| Productos | `http://localhost:8080/v3/api-docs/productos` |
| Pedidos | `http://localhost:8080/v3/api-docs/pedidos` |
| Pagos | `http://localhost:8080/v3/api-docs/pagos` |
| Notificaciones | `http://localhost:8080/v3/api-docs/notificaciones` |
| Reportes | `http://localhost:8080/v3/api-docs/reportes` |
| Checkout | `http://localhost:8080/v3/api-docs/checkout` |

Swagger individual por microservicio:

| Microservicio | URL |
|---|---|
| Autenticación | `http://localhost:8081/swagger-ui/index.html` |
| Usuarios | `http://localhost:8082/swagger-ui/index.html` |
| Productos | `http://localhost:8083/swagger-ui/index.html` |
| Pedidos | `http://localhost:8084/swagger-ui/index.html` |
| Pagos | `http://localhost:8085/swagger-ui/index.html` |
| Notificaciones | `http://localhost:8086/swagger-ui/index.html` |
| Reportes | `http://localhost:8087/swagger-ui/index.html` |
| Checkout | `http://localhost:8088/swagger-ui/index.html` |

---

## Ejecución local desde IntelliJ

Para ejecutar el sistema localmente desde IntelliJ:

1. Encender MySQL desde Laragon, XAMPP o servicio local.
2. Crear las bases de datos requeridas.
3. Abrir el proyecto en IntelliJ IDEA.
4. Ejecutar los microservicios en este orden recomendado:

```txt
1. ServicioAutenticacionApplication  → puerto 8081
2. ServicioUsuariosApplication       → puerto 8082
3. ServicioProductosApplication      → puerto 8083
4. ServicioPedidosApplication        → puerto 8084
5. ServicioPagosApplication          → puerto 8085
6. ServicioNotificacionesApplication → puerto 8086
7. ServicioReportesApplication       → puerto 8087
8. ServicioCheckoutApplication       → puerto 8088
9. ServicioGatewayApplication        → puerto 8080
```

El Gateway se ejecuta al final porque redirecciona hacia los demás microservicios.

---

## Ejecución con Docker Compose

El proyecto incluye Dockerfile en cada microservicio y un archivo `docker-compose.yml` en la carpeta raíz.

Antes de ejecutar Docker Compose:

1. Abrir Docker Desktop.
2. Encender MySQL local.
3. Verificar que MySQL esté activo en el puerto 3306.
4. Crear las bases de datos necesarias.

Verificar MySQL desde PowerShell:

```powershell
Test-NetConnection 127.0.0.1 -Port 3306
```

Debe responder:

```txt
TcpTestSucceeded : True
```

Construir imágenes:

```powershell
cd C:\Users\WindowsOS\Nexo_Commerce
docker compose build
```

Levantar todos los contenedores:

```powershell
cd C:\Users\WindowsOS\Nexo_Commerce
docker compose up
```

Levantar y reconstruir:

```powershell
cd C:\Users\WindowsOS\Nexo_Commerce
docker compose up --build
```

Ver contenedores:

```powershell
docker compose ps -a
```

Detener todos los contenedores:

```powershell
docker compose down
```

Ver logs de un servicio:

```powershell
docker compose logs servicio-checkout
```

Ejemplo de estado esperado:

```txt
servicio-autenticacion   Up
servicio-checkout        Up
servicio-gateway         Up
servicio-notificaciones  Up
servicio-pagos           Up
servicio-pedidos         Up
servicio-productos       Up
servicio-reportes        Up
servicio-usuarios        Up
```

---

## Endpoints principales

Todas las rutas siguientes pueden probarse desde el Gateway usando el puerto `8080`.

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

El microservicio `servicio-pedidos` se encarga de guardar y administrar pedidos.  
El flujo recomendado para crear un pedido completo es usar `servicio-checkout`.

### Crear pedido directamente

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
  "precioUnitario": 45990,
  "total": 91980
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

### Eliminar pedido

```http
DELETE http://localhost:8080/api/pedidos/1
```

---

## Checkout

El microservicio `servicio-checkout` orquesta el flujo de compra entre productos y pedidos.

### Test de funcionamiento

```http
GET http://localhost:8088/api/checkout/test
```

Respuesta esperada:

```txt
Servicio Checkout funcionando correctamente
```

### Realizar checkout

```http
POST http://localhost:8088/api/checkout
```

Body:

```json
{
  "correoUsuario": "juan@test.com",
  "productoId": 1,
  "cantidad": 2
}
```

Respuesta esperada:

```json
{
  "mensaje": "Checkout realizado correctamente",
  "pedidoId": 1,
  "correoUsuario": "juan@test.com",
  "productoId": 1,
  "nombreProducto": "Teclado Mecánico",
  "cantidad": 2,
  "precioUnitario": 45990.00,
  "total": 91980.00,
  "estado": "PENDIENTE"
}
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
1. Encender MySQL.
2. Levantar los microservicios desde IntelliJ o Docker Compose.
3. Crear un producto.
4. Ejecutar checkout usando el ID del producto.
5. Verificar que checkout consulte productos.
6. Verificar que checkout cree el pedido.
7. Listar pedidos para confirmar el registro.
8. Crear pago asociado al pedido.
9. Crear notificación.
10. Crear reporte.
```

Flujo principal validado:

```txt
Postman
→ servicio-checkout
→ servicio-productos
→ servicio-pedidos
→ MySQL
```

Resultado esperado del flujo checkout:

```txt
201 Created
Checkout realizado correctamente
```

---

## Manejo de errores

El sistema cuenta con manejo global de excepciones en los microservicios.

Cuando se envía un JSON vacío, el sistema responde con los campos requeridos:

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

El proyecto incluye pruebas unitarias con JUnit 5 y Mockito.

Pruebas implementadas:

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
- Creación, búsqueda, actualización, cancelación y eliminación de pedidos.
- Creación, aprobación y rechazo de pagos.
- Creación y actualización de notificaciones.
- Creación, consulta y eliminación de reportes.
- Verificación básica del Gateway.

Ejemplo de pruebas actualizadas:

- `PedidoServiceTest`: valida que pedidos reciba datos ya calculados desde checkout y guarde correctamente.
- `UsuarioServiceTest`: valida operaciones principales del servicio de usuarios usando mocks.

Para ejecutar pruebas desde IntelliJ:

```txt
Maven > Lifecycle > test
```

Para ejecutar pruebas desde terminal con Maven:

```powershell
mvn test
```

---

## Docker

Cada microservicio cuenta con su propio `Dockerfile`.

El proyecto padre cuenta con:

```txt
docker-compose.yml
```

Este archivo permite levantar todo el ecosistema de microservicios en contenedores independientes.

Comandos útiles:

```powershell
docker compose build
```

```powershell
docker compose up
```

```powershell
docker compose up --build
```

```powershell
docker compose down
```

```powershell
docker compose ps -a
```

```powershell
docker compose logs servicio-checkout
```

---

## Evidencia de funcionamiento

Se validó correctamente:

- Construcción de imágenes Docker.
- Ejecución de todos los contenedores con Docker Compose.
- Conexión con MySQL local.
- Funcionamiento del API Gateway.
- Funcionamiento de Swagger/OpenAPI.
- Comunicación entre `servicio-checkout`, `servicio-productos` y `servicio-pedidos`.
- Prueba real desde Postman con respuesta `201 Created`.

Respuesta obtenida en la prueba de checkout:

```json
{
  "mensaje": "Checkout realizado correctamente",
  "pedidoId": 1,
  "correoUsuario": "juan@test.com",
  "productoId": 1,
  "nombreProducto": "Teclado Mecánico",
  "cantidad": 2,
  "precioUnitario": 45990.00,
  "total": 91980.00,
  "estado": "PENDIENTE"
}
```

---

## Comandos Git utilizados

```bash
git status
git add .
git commit -m "mensaje del commit"
git push origin main
```

Commits importantes realizados:

```txt
agrega servicio checkout como orquestador
configura docker compose para microservicios
actualiza documentacion del proyecto
```

---

## Repositorio

Repositorio GitHub:

```txt
https://github.com/Dia1208/Nexo_Commerce.git
```

---

## Estado final del proyecto

El sistema queda organizado como una arquitectura de microservicios funcional, con:

- Separación por responsabilidades.
- Patrón CSR aplicado.
- Base de datos independiente por microservicio.
- Gateway como punto único de entrada.
- Autenticación mediante JWT.
- Comunicación REST entre microservicios con WebClient.
- Microservicio `servicio-checkout` como orquestador.
- Documentación Swagger individual y centralizada.
- Validaciones de datos.
- Manejo de errores personalizado.
- Pruebas unitarias.
- Dockerfile en cada microservicio.
- Docker Compose en el proyecto padre.
- Control de versiones con Git y GitHub.

---

## Observaciones

Para ejecutar correctamente el sistema se debe tener MySQL iniciado y las bases de datos creadas.

Si las tablas se eliminan o se recrean, es necesario volver a crear datos de prueba, especialmente productos, antes de ejecutar el flujo de checkout.

El Gateway se debe ejecutar al final cuando se trabaja desde IntelliJ, ya que depende de que los microservicios destino estén levantados.

Cuando se trabaja con Docker Compose, todos los microservicios se levantan juntos mediante:

```powershell
docker compose up
```