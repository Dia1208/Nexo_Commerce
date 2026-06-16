//1. Revisa si el correo ya existe.
//2. Encripta la contraseña.
//3. Guarda el usuario en MySQL.
//4. Genera un token JWT.
//5. Devuelve el token.
//
//Cuando hace login:
//
//1. Busca el usuario por correo.
//2. Compara la contraseña escrita con la contraseña encriptada.
//3. Si coincide, genera un JWT.
//4. Devuelve el token.

package com.nexocommerce.servicio_autenticacion.service;
import com.nexocommerce.servicio_autenticacion.dto.AuthResponse;
import com.nexocommerce.servicio_autenticacion.dto.LoginRequest;
import com.nexocommerce.servicio_autenticacion.dto.RegistroRequest;
import com.nexocommerce.servicio_autenticacion.entity.UsuarioAuth;
import com.nexocommerce.servicio_autenticacion.repository.UsuarioAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor// Genera automáticamente el constructor para inyectar los atributos finales (final)
public class AuthService {
    // Dependencias inyectadas automáticamente por Spring gracias a @RequiredArgsConstructor
    private final UsuarioAuthRepository usuarioAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Registra un nuevo usuario en el sistema, encripta su clave, lo guarda y le devuelve su primer token.
    public AuthResponse registrar(RegistroRequest request) {

        // 1. Valida que el correo no esté tomado. Si existe, frena el proceso lanzando una excepción.
        if (usuarioAuthRepository.existsByCorreo(request.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // 2. Mapea los datos recibidos (DTO) a la entidad de Base de Datos, aplicando BCrypt a la contraseña.
        UsuarioAuth usuario = UsuarioAuth.builder()
                .nombre(request.getNombre())
                .correo(request.getCorreo())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol("CLIENTE") // Por defecto, todo registro nuevo inicia con rol de cliente
                .build();

        // 3. Almacena el usuario en la base de datos y recupera el objeto guardado (que ya incluye su ID generado).
        UsuarioAuth usuarioGuardado = usuarioAuthRepository.save(usuario);

        // 4. Genera el token JWT usando los datos del usuario recién creado.
        String token = jwtService.generarToken(usuarioGuardado);

        // 5. Retorna la respuesta estandarizada con el token, el tipo de esquema (Bearer) y un mensaje de éxito.
        return new AuthResponse(
                token,
                "Bearer",
                "Usuario registrado correctamente"
        );
    }

    // Verifica las credenciales de un usuario existente y, si son correctas, le otorga un nuevo token de acceso.
    public AuthResponse login(LoginRequest request) {

        // 1. Busca al usuario por su correo. Si no lo encuentra, lanza un error genérico (por seguridad).
        UsuarioAuth usuario = usuarioAuthRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Correo o contraseña incorrectos"));

        // 2. Usa BCrypt para verificar si la contraseña ingresada coincide con el hash guardado en la BD.
        boolean passwordCorrecta = passwordEncoder.matches(
                request.getPassword(),
                usuario.getPassword()
        );

        // 3. Si la contraseña no coincide, frena el flujo inmediatamente.
        if (!passwordCorrecta) {
            throw new RuntimeException("Correo o contraseña incorrectos");
        }

        // 4. Si todo está en orden, genera un token JWT fresco para esta sesión.
        String token = jwtService.generarToken(usuario);

        // 5. Retorna la respuesta de autenticación exitosa.
        return new AuthResponse(
                token,
                "Bearer",
                "Login correcto"
        );}}