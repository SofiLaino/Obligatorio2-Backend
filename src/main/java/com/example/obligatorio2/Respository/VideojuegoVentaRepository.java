package com.example.obligatorio2.Respository;

import com.example.obligatorio2.Entity.VideojuegoVentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideojuegoVentaRepository extends JpaRepository<VideojuegoVentaEntity, Integer> {
    // Ejemplo de consulta personalizada (opcional)
    //List<VideojuegoVentaEntity> findByVideojuegoId(int videojuegoId);

    // Consulta personalizada para verificar si un usuario ha comprado un videojuego específico
    @Query("SELECT v FROM VideojuegoVentaEntity v JOIN v.venta ve WHERE ve.usuario.id = :usuarioId AND v.videojuego.id = :videojuegoId")
    Optional<VideojuegoVentaEntity> findCompraPorUsuarioYVideojuego(int usuarioId, int videojuegoId);


}
