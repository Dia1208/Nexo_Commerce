package com.nexocommerce.servicio_autenticacion.contoller;
import com.nexocommerce.servicio_autenticacion.dto.AuthResponse;
import com.nexocommerce.servicio_autenticacion.dto.LoginRequest;
import com.nexocommerce.servicio_autenticacion.dto.RegistroRequest;
import com.nexocommerce.servicio_autenticacion.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta clase es un controlador REST que manejará peticiones HTTP y devolverá respuestas en formato JSON
@RequestMapping("/api/auth") // Define la ruta base para todos los endpoints de esta clase (ej: http://localhost:8080/api/auth)
@RequiredArgsConstructor // Crea el constructor automático para inyectar el servicio de autenticación
public class AuthController {

    private final AuthService authService;

    // Endpoint POST para registrar usuarios. Valida los datos entrantes y responde con un código de estado 201 (Created).
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registrar(@Valid @RequestBody RegistroRequest request) {
        // @Valid: Activa las validaciones automáticas del DTO (como campos obligatorios o formato de correo)
        // @RequestBody: Le dice a Spring que extraiga los datos del cuerpo JSON de la petición HTTP
        AuthResponse response = authService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Endpoint POST para iniciar sesión. Valida las credenciales y responde con un código de estado 200 (OK) junto al token.
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    // Endpoint GET simple de prueba para verificar rápidamente desde el navegador o consola si el microservicio está respondiendo.
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Servicio de autenticación funcionando correctamente");
    }
}