package com.nexocommerce.servicio_pedidos.service;

import com.nexocommerce.servicio_pedidos.cliente.ProductoClient;
import com.nexocommerce.servicio_pedidos.dto.ActualizarEstadoPedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoResponse;
import com.nexocommerce.servicio_pedidos.dto.ProductoResponse;
import com.nexocommerce.servicio_pedidos.entity.EstadoPedido;
import com.nexocommerce.servicio_pedidos.entity.Pedido;
import com.nexocommerce.servicio_pedidos.exception.ResourceNotFoundException;
import com.nexocommerce.servicio_pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/*
 * Servicio encargado de la lógica de negocio relacionada con los pedidos.
 * Además, se comunica con el microservicio de productos mediante WebClient
 * para obtener información real del producto antes de crear el pedido.
 */
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoClient productoClient;

    /*
     * Constructor utilizado para inyectar el repositorio de pedidos
     * y el cliente REST de productos.
     */
    public PedidoService(PedidoRepository pedidoRepository, ProductoClient productoClient) {
        this.pedidoRepository = pedidoRepository;
        this.productoClient = productoClient;
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
     * Crea un pedido consultando primero los datos reales
     * del producto desde servicio-productos usando WebClient.
     */
    public PedidoResponse crear(PedidoRequest request) {
        ProductoResponse producto = productoClient.obtenerProductoPorId(request.getProductoId());

        if (producto == null) {
            throw new RuntimeException("No se pudo obtener el producto con id: " + request.getProductoId());
        }

        BigDecimal total = producto.getPrecio()
                .multiply(BigDecimal.valueOf(request.getCantidad()));

        Pedido pedido = Pedido.builder()
                .correoUsuario(request.getCorreoUsuario())
                .productoId(producto.getId())
                .nombreProducto(producto.getNombre())
                .cantidad(request.getCantidad())
                .precioUnitario(producto.getPrecio())
                .total(total)
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