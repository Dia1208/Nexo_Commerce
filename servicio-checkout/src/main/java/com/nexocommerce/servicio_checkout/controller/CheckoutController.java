package com.nexocommerce.servicio_checkout.controller;

import com.nexocommerce.servicio_checkout.dto.CheckoutRequest;
import com.nexocommerce.servicio_checkout.dto.CheckoutResponse;
import com.nexocommerce.servicio_checkout.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Controlador REST del microservicio checkout.
 */
@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    /*
     * Endpoint principal para realizar el checkout.
     */
    @PostMapping
    public ResponseEntity<CheckoutResponse> realizarCheckout(@Valid @RequestBody CheckoutRequest request) {
        CheckoutResponse response = checkoutService.realizarCheckout(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*
     * Endpoint simple para verificar que el servicio está funcionando.
     */
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Servicio Checkout funcionando correctamente");
    }
}