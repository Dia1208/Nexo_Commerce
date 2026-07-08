package com.nexocommerce.servicio_checkout.service;

import com.nexocommerce.servicio_checkout.client.PedidoClient;
import com.nexocommerce.servicio_checkout.client.ProductoClient;
import com.nexocommerce.servicio_checkout.dto.CheckoutRequest;
import com.nexocommerce.servicio_checkout.dto.CheckoutResponse;
import com.nexocommerce.servicio_checkout.dto.PedidoRequest;
import com.nexocommerce.servicio_checkout.dto.PedidoResponse;
import com.nexocommerce.servicio_checkout.dto.ProductoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/*
 * Servicio encargado de orquestar el proceso de checkout.
 */
@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final ProductoClient productoClient;
    private final PedidoClient pedidoClient;

    /*
     * Flujo principal:
     * 1. Recibe productoId, correoUsuario y cantidad.
     * 2. Consulta el producto en servicio-productos.
     * 3. Valida stock.
     * 4. Calcula el total.
     * 5. Envía el pedido calculado a servicio-pedidos.
     */
    public CheckoutResponse realizarCheckout(CheckoutRequest request) {

        ProductoResponse producto = productoClient.obtenerProductoPorId(request.getProductoId());

        if (producto == null) {
            throw new RuntimeException("Producto no encontrado");
        }

        if (producto.getStock() == null || producto.getStock() < request.getCantidad()) {
            throw new RuntimeException("Stock insuficiente para realizar el pedido");
        }

        BigDecimal total = producto.getPrecio()
                .multiply(BigDecimal.valueOf(request.getCantidad()));

        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setCorreoUsuario(request.getCorreoUsuario());
        pedidoRequest.setProductoId(producto.getId());
        pedidoRequest.setNombreProducto(producto.getNombre());
        pedidoRequest.setCantidad(request.getCantidad());
        pedidoRequest.setPrecioUnitario(producto.getPrecio());
        pedidoRequest.setTotal(total);

        PedidoResponse pedidoResponse = pedidoClient.crearPedido(pedidoRequest);

        return CheckoutResponse.builder()
                .mensaje("Checkout realizado correctamente")
                .pedidoId(pedidoResponse.getId())
                .correoUsuario(pedidoResponse.getCorreoUsuario())
                .productoId(pedidoResponse.getProductoId())
                .nombreProducto(pedidoResponse.getNombreProducto())
                .cantidad(pedidoResponse.getCantidad())
                .precioUnitario(pedidoResponse.getPrecioUnitario())
                .total(pedidoResponse.getTotal())
                .estado(pedidoResponse.getEstado())
                .build();
    }
}