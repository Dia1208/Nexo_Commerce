package com.nexocommerce.servicio_checkout.client;

import com.nexocommerce.servicio_checkout.dto.PedidoRequest;
import com.nexocommerce.servicio_checkout.dto.PedidoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/*
 * Cliente REST encargado de enviar pedidos al microservicio servicio-pedidos.
 */
@Component
@RequiredArgsConstructor
public class PedidoClient {

    private final WebClient.Builder webClientBuilder;

    /*
     * Crea un pedido en servicio-pedidos.
     */
    public PedidoResponse crearPedido(PedidoRequest request) {
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8084/api/pedidos")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PedidoResponse.class)
                .block();
    }
}