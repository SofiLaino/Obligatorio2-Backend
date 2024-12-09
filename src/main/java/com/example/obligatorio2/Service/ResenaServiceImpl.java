package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.ResenaEntity;
import com.example.obligatorio2.Entity.UsuarioEntity;
import com.example.obligatorio2.Entity.VideojuegoEntity;
import com.example.obligatorio2.Entity.VideojuegoVentaEntity;
import com.example.obligatorio2.Excepetions.BadRequestException;
import com.example.obligatorio2.Excepetions.EntityNotFoundException;
import com.example.obligatorio2.Respository.ResenaRepository;
import com.example.obligatorio2.Respository.UsuarioRepository;
import com.example.obligatorio2.Respository.VideojuegoRepository;
import com.example.obligatorio2.Respository.VideojuegoVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ResenaServiceImpl implements ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private VideojuegoVentaRepository videojuegoVentasRepository;  // Repositorio de ventas

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    // Verifica si el usuario puede dejar reseña
    public boolean puedeDejarResena(int usuarioId, int videojuegoId) {
        Optional<VideojuegoVentaEntity> compra = videojuegoVentasRepository.findCompraPorUsuarioYVideojuego(usuarioId, videojuegoId);
        return compra.isPresent(); // Devuelve true si la compra existe, false si no
    }

    public ResenaEntity agregarResena(int usuarioId, int videojuegoId, String resenaText, int calificacion, Date fecha) {
        if (puedeDejarResena(usuarioId, videojuegoId)) {
            // Obtener entidades relacionadas
            UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
            VideojuegoEntity videojuego = videojuegoRepository.findById(videojuegoId)
                    .orElseThrow(() -> new EntityNotFoundException("Videojuego no encontrado"));

            // Crear la reseña
            ResenaEntity resena = new ResenaEntity();
            resena.setUsuario(usuario); // Asigna la entidad UsuarioEntity
            resena.setVideojuego(videojuego); // Asigna la entidad VideojuegoEntity
            resena.setResena(resenaText);
            resena.setCalificacion(calificacion);
            resena.setFecha(fecha);

            return resenaRepository.save(resena);
        } else {
            throw new BadRequestException("El usuario no ha comprado el videojuego.");
        }
    }

    @Override
    public ResenaEntity save(ResenaEntity resenaEntity) {
        return resenaRepository.save(resenaEntity);
    }

    @Override
    public String delete(int id) {
        if (resenaRepository.existsById(id)) {
            resenaRepository.deleteById(id);
            return "Reseña eliminada";
        }
        throw new BadRequestException("La reseña con id " + id + " no existe");
    }

    @Override
    public ResenaEntity getById(int id) {
        return resenaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reseña no encontrada"));
    }

    @Override
    public List<ResenaEntity> getAll() {
        return resenaRepository.findAll();
    }
}
