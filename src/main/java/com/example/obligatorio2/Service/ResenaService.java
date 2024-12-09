package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.ResenaEntity;

import java.sql.Date;
import java.util.List;

public interface ResenaService {
    public ResenaEntity save(ResenaEntity resenaEntity);
    public String delete(int id);
    public ResenaEntity getById(int id);
    public List<ResenaEntity> getAll();

    // Método para verificar si el usuario puede dejar reseña
    boolean puedeDejarResena(int usuarioId, int videojuegoId);

    // Método para agregar reseña
    ResenaEntity agregarResena(int usuarioId, int videojuegoId, String resenaText, int calificacion, Date fecha);
}
