package com.nexocommerce.servicio_autenticacion.service;

import com.nexocommerce.servicio_autenticacion.entity.UsuarioAuth;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")// La clave maestra utilizada para firmar digitalmente el token.
    private String secret;

    @Value("${jwt.expiration}")//El tiempo de vida del token en milisegundos
    private Long expiration;

    public String generarToken(UsuarioAuth usuario) {
        Date fechaActual = new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime() + expiration);

        return Jwts.builder()
                .subject(usuario.getCorreo()) // Identificador principal (Sub)
                .claim("id", usuario.getId()) // Reclamación personalizada: ID
                .claim("nombre", usuario.getNombre()) // Reclamación personalizada: Nombre
                .claim("rol", usuario.getRol()) // Reclamación personalizada: Rol
                .issuedAt(fechaActual) // Fecha de emisión (Iat)
                .expiration(fechaExpiracion) // Fecha de expiración (Exp)
                .signWith(getSigningKey()) // Firma digital
                .compact(); // Construye el String final
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}