package com.nexocommerce.servicio_pedidos.client;

import com.nexocommerce.servicio_pedidos.dto.ProductoResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/*
 * Cliente REST encargado de comunicarse con el microservicio de productos.
 * Este cliente permite que servicio-pedidos consulte información real
 * del producto antes de crear un pedido.
 */
@Component
public class ProductoClient {

    private final WebClient.Builder webClientBuilder;

    /*
     * Constructor utilizado para inyectar WebClient.Builder.
     */
    public ProductoClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    /*
     * Consulta un producto por ID desde el microservicio de productos.
     *
     * Endpoint consumido:
     * GET http://localhost:8083/api/productos/{id}
     */
    public ProductoResponse obtenerProductoPorId(Long productoId) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/api/productos/{id}", productoId)
                .retrieve()
                .bodyToMono(ProductoResponse.class)
                .block();
    }
}