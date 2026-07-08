package com.nexocommerce.servicio_pedidos.service;

import com.nexocommerce.servicio_pedidos.dto.ActualizarEstadoPedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoResponse;
import com.nexocommerce.servicio_pedidos.entity.EstadoPedido;
import com.nexocommerce.servicio_pedidos.entity.Pedido;
import com.nexocommerce.servicio_pedidos.exception.ResourceNotFoundException;
import com.nexocommerce.servicio_pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Servicio encargado de la lógica de negocio relacionada con los pedidos.
 *
 * Este microservicio ya no consulta directamente al microservicio de productos.
 * La comunicación con productos y el cálculo del total ahora lo realiza
 * servicio-checkout, que actúa como microservicio orquestador.
 *
 * servicio-pedidos queda encargado de registrar, listar, buscar,
 * actualizar estado, cancelar y eliminar pedidos.
 */
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    /*
     * Constructor utilizado para inyectar el repositorio de pedidos.
     */
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    /*
     * Lista todos los pedidos registrados.
     */
    public List<PedidoResponse> listar() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    /*
     * Busca un pedido por su ID.
     */
    public PedidoResponse buscarPorId(Long id) {
        Pedido pedido = obtenerPedidoPorId(id);
        return mapearAResponse(pedido);
    }

    /*
     * Lista todos los pedidos realizados por un usuario específico.
     */
    public List<PedidoResponse> listarPorUsuario(String correoUsuario) {
        return pedidoRepository.findByCorreoUsuario(correoUsuario)
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    /*
     * Crea un pedido con los datos ya calculados por servicio-checkout.
     *
     * servicio-checkout consulta productos, valida stock y calcula el total.
     * servicio-pedidos solo guarda el pedido y asigna el estado inicial PENDIENTE.
     */
    public PedidoResponse crear(PedidoRequest request) {

        Pedido pedido = Pedido.builder()
                .correoUsuario(request.getCorreoUsuario())
                .productoId(request.getProductoId())
                .nombreProducto(request.getNombreProducto())
                .cantidad(request.getCantidad())
                .precioUnitario(request.getPrecioUnitario())
                .total(request.getTotal())
                .estado(EstadoPedido.PENDIENTE)
                .fechaCreacion(LocalDateTime.now())
                .build();

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        return mapearAResponse(pedidoGuardado);
    }

    /*
     * Actualiza el estado de un pedido existente.
     */
    public PedidoResponse actualizarEstado(Long id, ActualizarEstadoPedidoRequest request) {
        Pedido pedido = obtenerPedidoPorId(id);
        pedido.setEstado(request.getEstado());

        Pedido pedidoActualizado = pedidoRepository.save(pedido);

        return mapearAResponse(pedidoActualizado);
    }

    /*
     * Cancela un pedido existente.
     */
    public PedidoResponse cancelar(Long id) {
        Pedido pedido = obtenerPedidoPorId(id);
        pedido.setEstado(EstadoPedido.CANCELADO);

        Pedido pedidoCancelado = pedidoRepository.save(pedido);

        return mapearAResponse(pedidoCancelado);
    }

    /*
     * Elimina un pedido de la base de datos.
     */
    public void eliminar(Long id) {
        Pedido pedido = obtenerPedidoPorId(id);
        pedidoRepository.delete(pedido);
    }

    /*
     * Busca un pedido por ID o lanza excepción si no existe.
     */
    private Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id: " + id));
    }

    /*
     * Convierte una entidad Pedido en PedidoResponse.
     */
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