package com.nexocommerce.servicio_pedidos;

import com.nexocommerce.servicio_pedidos.entity.EstadoPedido;
import com.nexocommerce.servicio_pedidos.entity.Pedido;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServicioPedidosApplicationTests {

	/*
	 * Prueba básica para verificar que el contexto
	 * de Spring Boot carga correctamente.
	 */
	@Test
	void contextLoads() {
	}

	/*
	 * Prueba básica de creación de un pedido.
	 * Se valida que los datos asignados al objeto Pedido
	 * sean los mismos que se obtienen desde sus getters.
	 */
	@Test
	void crearPedidoCorrectamente() {
		Pedido pedido = new Pedido();

		pedido.setId(1L);
		pedido.setCorreoUsuario("juan@test.com");
		pedido.setProductoId(1L);
		pedido.setNombreProducto("Teclado Mecánico");
		pedido.setCantidad(2);
		pedido.setPrecioUnitario(new BigDecimal("45990"));
		pedido.setTotal(new BigDecimal("91980"));
		pedido.setEstado(EstadoPedido.PENDIENTE);
		pedido.setFechaCreacion(LocalDateTime.now());

		assertNotNull(pedido);
		assertEquals(1L, pedido.getId());
		assertEquals("juan@test.com", pedido.getCorreoUsuario());
		assertEquals(1L, pedido.getProductoId());
		assertEquals("Teclado Mecánico", pedido.getNombreProducto());
		assertEquals(2, pedido.getCantidad());
		assertEquals(new BigDecimal("45990"), pedido.getPrecioUnitario());
		assertEquals(new BigDecimal("91980"), pedido.getTotal());
		assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
		assertNotNull(pedido.getFechaCreacion());
	}

	/*
	 * Prueba básica para verificar que el estado
	 * de un pedido puede actualizarse correctamente.
	 */
	@Test
	void actualizarEstadoPedidoCorrectamente() {
		Pedido pedido = new Pedido();

		pedido.setEstado(EstadoPedido.PENDIENTE);

		assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());

		pedido.setEstado(EstadoPedido.ENVIADO);

		assertEquals(EstadoPedido.ENVIADO, pedido.getEstado());
		assertNotEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
	}

	/*
	 * Prueba básica para verificar el cálculo
	 * del total de un pedido.
	 */
	@Test
	void calcularTotalPedidoCorrectamente() {
		BigDecimal precioUnitario = new BigDecimal("45990");
		Integer cantidad = 2;

		BigDecimal total = precioUnitario.multiply(BigDecimal.valueOf(cantidad));

		assertEquals(new BigDecimal("91980"), total);
	}
	@Test
	void pruebaEstadoPedidoNoEsEnviado() {

		Pedido pedido = new Pedido();
		pedido.setEstado(EstadoPedido.PENDIENTE);
		assertNotEquals(EstadoPedido.ENVIADO, pedido.getEstado());
		System.out.println("El pedido no está enviado");
	}

}