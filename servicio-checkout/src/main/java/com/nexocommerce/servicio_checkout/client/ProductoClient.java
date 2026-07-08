package com.nexocommerce.servicio_checkout.client;

import com.nexocommerce.servicio_checkout.dto.ProductoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ProductoClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${servicios.productos.url:http://localhost:8083}")
    private String productosUrl;

    public ProductoResponse obtenerProductoPorId(Long productoId) {
        return webClientBuilder.build()
                .get()
                .uri(productosUrl + "/api/productos/{id}", productoId)
                .retrieve()
                .bodyToMono(ProductoResponse.class)
                .block();
    }
}