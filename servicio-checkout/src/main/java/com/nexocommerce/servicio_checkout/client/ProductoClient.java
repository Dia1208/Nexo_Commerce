package com.nexocommerce.servicio_checkout.client;

import com.nexocommerce.servicio_checkout.dto.ProductoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/*
 * Cliente REST encargado de consultar el microservicio de productos.
 */
@Component
@RequiredArgsConstructor
public class ProductoClient {

    private final WebClient.Builder webClientBuilder;

    /*
     * Consulta un producto por ID desde servicio-productos.
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