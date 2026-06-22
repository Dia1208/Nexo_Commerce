package com.nexocommerce.servicio_usuarios.service;

import com.nexocommerce.servicio_usuarios.model.UsuarioModel;
import com.nexocommerce.servicio_usuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
 * Pruebas unitarias del servicio de usuarios.
 * Se prueban los métodos de listar, buscar, buscar por correo,
 * guardar, actualizar y eliminar usuarios.
 *
 * Estas pruebas usan Mockito para simular el repositorio,
 * evitando depender de la base de datos real.
 */
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioModel usuario;

    /*
     * Datos base que se reutilizan en las pruebas.
     */
    @BeforeEach
    void setUp() {
        usuario = UsuarioModel.builder()
                .id(1L)
                .nombre("Juan")
                .correo("juan@test.com")
                .telefono("123456789")
                .direccion("Calle 123")
                .rol("CLIENTE")
                .build();
    }

    @Test
    void listarUsuariosCorrectamente() {
        // Given: se simula que el repositorio retorna una lista con un usuario.
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        // When: se ejecuta el método listar del servicio.
        List<UsuarioModel> resultado = usuarioService.listar();

        // Then: se valida que la lista tenga el usuario esperado.
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
        assertEquals("juan@test.com", resultado.get(0).getCorreo());

        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void buscarUsuarioPorIdCorrectamente() {
        // Given: se simula que el usuario existe en el repositorio.
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // When: se ejecuta la búsqueda por ID.
        UsuarioModel resultado = usuarioService.buscar(1L);

        // Then: se valida que el usuario retornado sea correcto.
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan", resultado.getNombre());
        assertEquals("juan@test.com", resultado.getCorreo());

        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void buscarUsuarioNoExistenteLanzaExcepcion() {
        // Given: se simula que el usuario no existe.
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        // When / Then: se valida que el servicio lance una excepción.
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> usuarioService.buscar(99L));

        assertEquals("Usuario no encontrado con id: 99", exception.getMessage());

        verify(usuarioRepository, times(1)).findById(99L);
    }

    @Test
    void buscarUsuarioPorCorreoCorrectamente() {
        // Given: se simula que existe un usuario con ese correo.
        when(usuarioRepository.findByCorreo("juan@test.com")).thenReturn(Optional.of(usuario));

        // When: se ejecuta la búsqueda por correo.
        UsuarioModel resultado = usuarioService.buscarPorCorreo("juan@test.com");

        // Then: se valida que el usuario retornado sea correcto.
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        assertEquals("juan@test.com", resultado.getCorreo());

        verify(usuarioRepository, times(1)).findByCorreo("juan@test.com");
    }

    @Test
    void buscarUsuarioPorCorreoNoExistenteLanzaExcepcion() {
        // Given: se simula que no existe un usuario con ese correo.
        when(usuarioRepository.findByCorreo("noexiste@test.com")).thenReturn(Optional.empty());

        // When / Then: se valida que el servicio lance una excepción.
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> usuarioService.buscarPorCorreo("noexiste@test.com"));

        assertEquals("Usuario no encontrado con correo: noexiste@test.com", exception.getMessage());

        verify(usuarioRepository, times(1)).findByCorreo("noexiste@test.com");
    }

    @Test
    void guardarUsuarioCorrectamente() {
        // Given: se simula que el correo no está registrado y que el usuario se guarda correctamente.
        when(usuarioRepository.existsByCorreo("juan@test.com")).thenReturn(false);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // When: se ejecuta el método guardar.
        UsuarioModel resultado = usuarioService.guardar(usuario);

        // Then: se valida que el usuario guardado sea correcto.
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        assertEquals("juan@test.com", resultado.getCorreo());

        verify(usuarioRepository, times(1)).existsByCorreo("juan@test.com");
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void guardarUsuarioConCorreoDuplicadoLanzaExcepcion() {
        // Given: se simula que el correo ya está registrado.
        when(usuarioRepository.existsByCorreo("juan@test.com")).thenReturn(true);

        // When / Then: se valida que el servicio no permita guardar el usuario.
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> usuarioService.guardar(usuario));

        assertEquals("El correo ya está registrado", exception.getMessage());

        verify(usuarioRepository, times(1)).existsByCorreo("juan@test.com");
        verify(usuarioRepository, never()).save(any(UsuarioModel.class));
    }

    @Test
    void actualizarUsuarioCorrectamente() {
        // Given: datos nuevos para actualizar el usuario.
        UsuarioModel datosActualizados = UsuarioModel.builder()
                .nombre("Juan Actualizado")
                .correo("juanactualizado@test.com")
                .telefono("987654321")
                .direccion("Nueva dirección")
                .rol("CLIENTE")
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.existsByCorreo("juanactualizado@test.com")).thenReturn(false);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // When: se ejecuta la actualización.
        UsuarioModel resultado = usuarioService.actualizar(1L, datosActualizados);

        // Then: se valida que los datos hayan cambiado correctamente.
        assertNotNull(resultado);
        assertEquals("Juan Actualizado", resultado.getNombre());
        assertEquals("juanactualizado@test.com", resultado.getCorreo());
        assertEquals("987654321", resultado.getTelefono());
        assertEquals("Nueva dirección", resultado.getDireccion());
        assertEquals("CLIENTE", resultado.getRol());

        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).existsByCorreo("juanactualizado@test.com");
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void actualizarUsuarioManteniendoMismoCorreoCorrectamente() {
        // Given: datos actualizados, pero manteniendo el mismo correo.
        UsuarioModel datosActualizados = UsuarioModel.builder()
                .nombre("Juan Actualizado")
                .correo("juan@test.com")
                .telefono("987654321")
                .direccion("Nueva dirección")
                .rol("CLIENTE")
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // When: se ejecuta la actualización.
        UsuarioModel resultado = usuarioService.actualizar(1L, datosActualizados);

        // Then: se valida que los datos se actualicen correctamente.
        assertNotNull(resultado);
        assertEquals("Juan Actualizado", resultado.getNombre());
        assertEquals("juan@test.com", resultado.getCorreo());
        assertEquals("987654321", resultado.getTelefono());
        assertEquals("Nueva dirección", resultado.getDireccion());
        assertEquals("CLIENTE", resultado.getRol());

        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, never()).existsByCorreo(anyString());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void actualizarUsuarioConCorreoDuplicadoLanzaExcepcion() {
        // Given: datos nuevos con un correo que ya existe.
        UsuarioModel datosActualizados = UsuarioModel.builder()
                .nombre("Juan Actualizado")
                .correo("otro@test.com")
                .telefono("987654321")
                .direccion("Nueva dirección")
                .rol("CLIENTE")
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.existsByCorreo("otro@test.com")).thenReturn(true);

        // When / Then: se valida que el servicio lance una excepción.
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> usuarioService.actualizar(1L, datosActualizados));

        assertEquals("El correo ya está registrado", exception.getMessage());

        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).existsByCorreo("otro@test.com");
        verify(usuarioRepository, never()).save(any(UsuarioModel.class));
    }

    @Test
    void eliminarUsuarioCorrectamente() {
        // Given: se simula que el usuario existe.
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // When: se ejecuta la eliminación.
        usuarioService.eliminar(1L);

        // Then: se valida que primero se buscó y luego se eliminó.
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).delete(usuario);
    }

    @Test
    void eliminarUsuarioNoExistenteLanzaExcepcion() {
        // Given: se simula que el usuario no existe.
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        // When / Then: se valida que el servicio lance una excepción.
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> usuarioService.eliminar(99L));

        assertEquals("Usuario no encontrado con id: 99", exception.getMessage());

        verify(usuarioRepository, times(1)).findById(99L);
        verify(usuarioRepository, never()).delete(any(UsuarioModel.class));
    }
}