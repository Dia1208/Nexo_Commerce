package com.nexocommerce.servicio_pedidos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/*
 * DTO utilizado para recibir los datos necesarios
 * al crear un nuevo pedido.
 *
 * Ahora servicio-pedidos no consulta productos directamente.
 * El pedido puede ser creado por servicio-checkout, que ya consulta
 * el producto, valida los datos, calcula el total y envía
 * la información lista para guardar.
 */
@Data
public class PedidoRequest {

    @NotBlank(message = "El correo del usuario es obligatorio")
    private String correoUsuario;

    @NotNull(message = "El id del producto es obligatorio")
    private Long productoId;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombreProducto;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    private BigDecimal precioUnitario;

    @NotNull(message = "El total es obligatorio")
    private BigDecimal total;
}