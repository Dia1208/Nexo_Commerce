package com.nexocommerce.servicio_pedidos.service;

import com.nexocommerce.servicio_pedidos.dto.ActualizarEstadoPedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoResponse;
import com.nexocommerce.servicio_pedidos.entity.EstadoPedido;
import com.nexocommerce.servicio_pedidos.entity.Pedido;
import com.nexocommerce.servicio_pedidos.exception.ResourceNotFoundException;
import com.nexocommerce.servicio_pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/*
 * Servicio encargado de la lógica de negocio relacionada con los pedidos.
 */
@Service

/*
 * Genera automáticamente el constructor para inyectar
 * el repositorio PedidoRepository en este servicio.
 */
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    // Lista todos los pedidos registrados.
    public List<PedidoResponse> listar() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    // Busca un pedido por su identificador.
    public PedidoResponse buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id: " + id));

        return mapearAResponse(pedido);
    }

    // Lista todos los pedidos realizados por un usuario específico.
    public List<PedidoResponse> listarPorUsuario(String correoUsuario) {
        return pedidoRepository.findByCorreoUsuario(correoUsuario)
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    // Crea un nuevo pedido y calcula automáticamente el total.
    public PedidoResponse crear(PedidoRequest request) {
        BigDecimal total = request.getPrecioUnitario()
                .multiply(BigDecimal.valueOf(request.getCantidad()));

        Pedido pedido = Pedido.builder()
                .correoUsuario(request.getCorreoUsuario())
                .productoId(request.getProductoId())
                .nombreProducto(request.getNombreProducto())
                .cantidad(request.getCantidad())
                .precioUnitario(request.getPrecioUnitario())
                .total(total)
                .estado(EstadoPedido.PENDIENTE)
                .fechaCreacion(LocalDateTime.now())
                .build();

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        return mapearAResponse(pedidoGuardado);
    }

    // Actualiza el estado de un pedido existente.
    public PedidoResponse actualizarEstado(Long id, ActualizarEstadoPedidoRequest request) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id: " + id));

        pedido.setEstado(request.getEstado());

        Pedido pedidoActualizado = pedidoRepository.save(pedido);

        return mapearAResponse(pedidoActualizado);
    }

    // Cancela un pedido cambiando su estado a CANCELADO.
    public PedidoResponse cancelar(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id: " + id));

        pedido.setEstado(EstadoPedido.CANCELADO);

        Pedido pedidoCancelado = pedidoRepository.save(pedido);

        return mapearAResponse(pedidoCancelado);
    }

    // Convierte una entidad Pedido en un DTO PedidoResponse.
    private PedidoResponse mapearAResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getCorreoUsuario(),
                pedido.getProductoId(),
                pedido.getNombreProducto(),
                pedido.getCantidad(),
                pedido.getPrecioUnitario(),
                pedido.getTotal(),
                pedido.getEstado(),
                pedido.getFechaCreacion()
        );
    }
}