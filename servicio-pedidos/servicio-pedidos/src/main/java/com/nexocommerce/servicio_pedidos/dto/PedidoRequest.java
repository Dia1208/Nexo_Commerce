package com.nexocommerce.servicio_pedidos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/*
 * DTO utilizado para recibir los datos necesarios
 * al crear un nuevo pedido.
 */
@Data
public class PedidoRequest {

    // Correo del usuario que realiza el pedido.
    @NotBlank(message = "El correo del usuario es obligatorio")
    private String correoUsuario;

    // Identificador del producto solicitado.
    @NotNull(message = "El id del producto es obligatorio")
    private Long productoId;

    // Nombre del producto solicitado.
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombreProducto;

    // Cantidad de unidades solicitadas.
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    // Precio unitario del producto.
    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "1.0", message = "El precio unitario debe ser mayor a 0")
    private BigDecimal precioUnitario;
}