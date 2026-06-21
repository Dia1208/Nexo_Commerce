package com.nexocommerce.servicio_pedidos.dto;

import com.nexocommerce.servicio_pedidos.entity.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * DTO utilizado para devolver la información de un pedido
 * como respuesta al cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {

    // Identificador único del pedido.
    private Long id;

    // Correo del usuario que realizó el pedido.
    private String correoUsuario;

    // Identificador del producto comprado.
    private Long productoId;

    // Nombre del producto comprado.
    private String nombreProducto;

    // Cantidad de unidades solicitadas.
    private Integer cantidad;

    // Precio unitario del producto.
    private BigDecimal precioUnitario;

    // Total calculado del pedido.
    private BigDecimal total;

    // Estado actual del pedido.
    private EstadoPedido estado;

    // Fecha en la que se creó el pedido.
    private LocalDateTime fechaCreacion;
}