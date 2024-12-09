
package com.example.obligatorio2.Respository;

import com.example.obligatorio2.Entity.TarjetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List; // Asegúrate de usar la interfaz estándar de Java

public interface TarjetaRepository extends JpaRepository<TarjetaEntity, Integer> {
    @Query("SELECT t FROM TarjetaEntity t WHERE t.usuario.id = :usuarioId")
    List<TarjetaEntity> findByUsuarioId(@Param("usuarioId") int usuarioId);
}


