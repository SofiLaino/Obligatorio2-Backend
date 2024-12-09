package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.VideojuegoVentaEntity;

import java.util.List;
import java.util.Optional;

public interface VideojuegoVentaService {

    /**
     * Guarda o actualiza una instancia de VideojuegoVentaEntity.
     *
     * @param videojuegoVenta La entidad a guardar o actualizar.
     * @return La entidad guardada.
     */
    VideojuegoVentaEntity save(VideojuegoVentaEntity videojuegoVenta);

    /**
     * Obtiene todas las relaciones de videojuegos vendidos.
     *
     * @return Una lista de entidades VideojuegoVentaEntity.
     */
    List<VideojuegoVentaEntity> getAll();

    /**
     * Verifica si un usuario ha comprado un videojuego específico.
     *
     * @param usuarioId El ID del usuario.
     * @param videojuegoId El ID del videojuego.
     * @return Un Optional que contiene la entidad VideojuegoVentaEntity si existe la compra, o vacío si no.
     */
    Optional<VideojuegoVentaEntity> verificarCompra(int usuarioId, int videojuegoId);
}
