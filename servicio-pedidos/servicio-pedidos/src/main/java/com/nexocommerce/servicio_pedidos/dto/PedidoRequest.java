package com.nexocommerce.servicio_pedidos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
 * DTO utilizado para recibir los datos necesarios
 * al crear un nuevo pedido.
 *
 * El nombre y precio del producto ya no se envían manualmente,
 * porque ahora se consultan desde el microservicio de productos
 * mediante WebClient.
 */
@Data
public class PedidoRequest {

    // Correo del usuario que realiza el pedido.
    @NotBlank(message = "El correo del usuario es obligatorio")
    private String correoUsuario;

    // Identificador del producto solicitado.
    @NotNull(message = "El id del producto es obligatorio")
    private Long productoId;

    // Cantidad de unidades solicitadas.
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;
}