package com.example.obligatorio2.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.obligatorio2.Entity.VentaEntity;

import java.sql.Date;
import java.util.List;

public interface VentaRepository  extends JpaRepository<VentaEntity, Integer> {
    List<VentaEntity> findByUsuarioId(int usuarioId);
    List<VentaEntity> findByFechaCompraBetween(Date fechaInicio, Date fechaFin);
}
