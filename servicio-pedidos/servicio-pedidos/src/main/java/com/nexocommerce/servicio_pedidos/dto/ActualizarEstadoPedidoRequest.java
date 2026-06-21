package com.nexocommerce.servicio_pedidos.dto;

import com.nexocommerce.servicio_pedidos.entity.EstadoPedido;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
 * DTO utilizado para actualizar el estado de un pedido.
 * Recibe el nuevo estado que se desea asignar al pedido.
 */
@Data
public class ActualizarEstadoPedidoRequest {

    // Nuevo estado del pedido.
    @NotNull(message = "El estado es obligatorio")
    private EstadoPedido estado;
}