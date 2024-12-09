package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.VideojuegoVentaEntity;
import com.example.obligatorio2.Respository.VideojuegoVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideojuegoVentaServiceImpl implements VideojuegoVentaService {

    @Autowired
    private VideojuegoVentaRepository videojuegoVentaRepository;

    /**
     * Guarda o actualiza una relación entre un videojuego y una venta.
     *
     * @param videojuegoVenta La entidad a guardar o actualizar.
     * @return La entidad guardada.
     */
    @Override
    public VideojuegoVentaEntity save(VideojuegoVentaEntity videojuegoVenta) {
        return videojuegoVentaRepository.save(videojuegoVenta);
    }

    /**
     * Obtiene todas las relaciones de videojuegos vendidos.
     *
     * @return Una lista de VideojuegoVentaEntity.
     */
    @Override
    public List<VideojuegoVentaEntity> getAll() {
        return videojuegoVentaRepository.findAll();
    }

    /**
     * Verifica si un usuario ha comprado un videojuego específico.
     *
     * @param usuarioId El ID del usuario.
     * @param videojuegoId El ID del videojuego.
     * @return Un Optional que contiene la entidad si existe la compra, o vacío si no.
     */
    @Override
    public Optional<VideojuegoVentaEntity> verificarCompra(int usuarioId, int videojuegoId) {
        return videojuegoVentaRepository.findCompraPorUsuarioYVideojuego(usuarioId, videojuegoId);
    }
}