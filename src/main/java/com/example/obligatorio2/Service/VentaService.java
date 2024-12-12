package com.example.obligatorio2.Service;

import com.example.obligatorio2.EntitiesDTOs.VentaDTO;
import com.example.obligatorio2.Entity.VentaEntity;

import java.sql.Date;
import java.util.List;

public interface VentaService {

    VentaEntity save(VentaEntity ventaEntity);
    String delete(int id);
    VentaEntity getById(int id);
    List<VentaDTO> getAllVentasDTO();
    VentaDTO getVentaDTOById(int id);
    List<VentaDTO> getVentasPorUsuario(int usuarioId);
    List<VentaDTO> getVentasPorFecha(Date fechainicio, Date fechaFin);

    List<VentaDTO> getVentasPorUsuarioId(int usuarioId);
}
