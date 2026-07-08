package com.nexocommerce.servicio_checkout.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
 * DTO que recibe los datos necesarios para iniciar un checkout.
 */
@Data
public class CheckoutRequest {

    @NotBlank(message = "El correo del usuario es obligatorio")
    private String correoUsuario;

    @NotNull(message = "El id del producto es obligatorio")
    private Long productoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;
}