package com.nexocommerce.servicio_checkout.client;

import com.nexocommerce.servicio_checkout.dto.PedidoRequest;
import com.nexocommerce.servicio_checkout.dto.PedidoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class PedidoClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${servicios.pedidos.url:http://localhost:8084}")
    private String pedidosUrl;

    public PedidoResponse crearPedido(PedidoRequest request) {
        return webClientBuilder.build()
                .post()
                .uri(pedidosUrl + "/api/pedidos")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PedidoResponse.class)
                .block();
    }
}