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
 * Se prueban los métodos de listar, buscar, guardar,
 * actualizar y eliminar usuarios.
 */
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioModel usuario;

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
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<UsuarioModel> resultado = usuarioService.listar();

        assertEquals(1, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
    }

    @Test
    void buscarUsuarioPorIdCorrectamente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioModel resultado = usuarioService.buscar(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("juan@test.com", resultado.getCorreo());
    }

    @Test
    void buscarUsuarioNoExistenteLanzaExcepcion() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> usuarioService.buscar(99L));

        assertEquals("Usuario no encontrado", exception.getMessage());
    }

    @Test
    void guardarUsuarioCorrectamente() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioModel resultado = usuarioService.guardar(usuario);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());

        verify(usuarioRepository).save(usuario);
    }

    @Test
    void actualizarUsuarioCorrectamente() {
        UsuarioModel datosActualizados = UsuarioModel.builder()
                .nombre("Juan Actualizado")
                .correo("juanactualizado@test.com")
                .telefono("987654321")
                .direccion("Nueva dirección")
                .rol("CLIENTE")
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioModel resultado = usuarioService.actualizar(1L, datosActualizados);

        assertNotNull(resultado);
        assertEquals("Juan Actualizado", usuario.getNombre());
        assertEquals("juanactualizado@test.com", usuario.getCorreo());
        assertEquals("987654321", usuario.getTelefono());
        assertEquals("Nueva dirección", usuario.getDireccion());

        verify(usuarioRepository).save(usuario);
    }

    @Test
    void eliminarUsuarioCorrectamente() {
        usuarioService.eliminar(1L);

        verify(usuarioRepository).deleteById(1L);
    }
}