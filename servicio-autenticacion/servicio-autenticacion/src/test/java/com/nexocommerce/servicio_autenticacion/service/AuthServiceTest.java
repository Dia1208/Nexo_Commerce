package com.nexocommerce.servicio_autenticacion.service;

import com.nexocommerce.servicio_autenticacion.dto.AuthResponse;
import com.nexocommerce.servicio_autenticacion.dto.LoginRequest;
import com.nexocommerce.servicio_autenticacion.dto.RegistroRequest;
import com.nexocommerce.servicio_autenticacion.entity.UsuarioAuth;
import com.nexocommerce.servicio_autenticacion.repository.UsuarioAuthRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
 * Pruebas unitarias del servicio de autenticación.
 * Se prueban los métodos de registro y login.
 */
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UsuarioAuthRepository usuarioAuthRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    private RegistroRequest registroRequest;
    private LoginRequest loginRequest;
    private UsuarioAuth usuario;

    @BeforeEach
    void setUp() {
        registroRequest = new RegistroRequest();
        registroRequest.setNombre("Juan");
        registroRequest.setCorreo("juan@test.com");
        registroRequest.setPassword("123456");

        loginRequest = new LoginRequest();
        loginRequest.setCorreo("juan@test.com");
        loginRequest.setPassword("123456");

        usuario = UsuarioAuth.builder()
                .id(1L)
                .nombre("Juan")
                .correo("juan@test.com")
                .password("password-encriptada")
                .rol("CLIENTE")
                .build();
    }

    @Test
    void registrarUsuarioCorrectamente() {
        when(usuarioAuthRepository.existsByCorreo("juan@test.com")).thenReturn(false);
        when(passwordEncoder.encode("123456")).thenReturn("password-encriptada");
        when(usuarioAuthRepository.save(any(UsuarioAuth.class))).thenReturn(usuario);
        when(jwtService.generarToken(usuario)).thenReturn("token-jwt");

        AuthResponse response = authService.registrar(registroRequest);

        assertNotNull(response);
        assertEquals("token-jwt", response.getToken());
        assertEquals("Bearer", response.getTipo());
        assertEquals("Usuario registrado correctamente", response.getMensaje());

        verify(usuarioAuthRepository).save(any(UsuarioAuth.class));
    }

    @Test
    void registrarUsuarioConCorreoRepetidoLanzaExcepcion() {
        when(usuarioAuthRepository.existsByCorreo("juan@test.com")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authService.registrar(registroRequest));

        assertEquals("El correo ya está registrado", exception.getMessage());
        verify(usuarioAuthRepository, never()).save(any());
    }

    @Test
    void loginCorrectamente() {
        when(usuarioAuthRepository.findByCorreo("juan@test.com")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("123456", "password-encriptada")).thenReturn(true);
        when(jwtService.generarToken(usuario)).thenReturn("token-jwt");

        AuthResponse response = authService.login(loginRequest);

        assertNotNull(response);
        assertEquals("token-jwt", response.getToken());
        assertEquals("Bearer", response.getTipo());
        assertEquals("Login correcto", response.getMensaje());
    }

    @Test
    void loginConCorreoInexistenteLanzaExcepcion() {
        when(usuarioAuthRepository.findByCorreo("juan@test.com")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authService.login(loginRequest));

        assertEquals("Correo o contraseña incorrectos", exception.getMessage());
    }

    @Test
    void loginConPasswordIncorrectaLanzaExcepcion() {
        when(usuarioAuthRepository.findByCorreo("juan@test.com")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("123456", "password-encriptada")).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authService.login(loginRequest));

        assertEquals("Correo o contraseña incorrectos", exception.getMessage());
    }
}