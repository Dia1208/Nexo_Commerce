package com.nexocommerce.servicio_notificaciones.repository;

import com.nexocommerce.servicio_notificaciones.entity.Notificacion;
import com.nexocommerce.servicio_notificaciones.entity.TipoNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * Esta interfaz permite realizar operaciones CRUD sobre notificaciones.
 * Spring Data JPA genera automáticamente los métodos básicos.
 */
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    // Busca notificaciones asociadas a un usuario.
    List<Notificacion> findByCorreoUsuario(String correoUsuario);

    // Busca notificaciones por tipo.
    List<Notificacion> findByTipo(TipoNotificacion tipo);
}