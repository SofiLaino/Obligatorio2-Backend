package com.example.obligatorio2.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.obligatorio2.Entity.VentaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface VentaRepository extends JpaRepository<VentaEntity, Integer> {
    List<VentaEntity> findByFechaCompraBetween(Date fechaInicio, Date fechaFin);

    @Query("SELECT v FROM VentaEntity v WHERE v.usuario.id = :usuarioId")
    List<VentaEntity> findByUsuarioId(@Param("usuarioId") int usuarioId);

}
