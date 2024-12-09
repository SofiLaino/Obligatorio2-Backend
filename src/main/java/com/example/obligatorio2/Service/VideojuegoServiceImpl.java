package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.VideojuegoEntity;
import com.example.obligatorio2.Excepetions.BadRequestException;
import com.example.obligatorio2.Excepetions.EntityNotFoundException;
import com.example.obligatorio2.Respository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideojuegoServiceImpl  implements VideojuegoService{

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    public VideojuegoEntity save(VideojuegoEntity videojuegoEntity){
        try {
            return videojuegoRepository.save(videojuegoEntity);

        }catch (Exception ex){
            throw new BadRequestException("Ya existe ese videojuego");
        }
    }
    public String delete(int id){
        if(videojuegoRepository.existsById(id)){
            videojuegoRepository.deleteById(id);
            return "Videojuego eliminado";
        }
        throw new BadRequestException("El videojuego con id " + id +" no existe");
    }
    public VideojuegoEntity getById(int id){
        return videojuegoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Videojuego no encontrado "));
    }
    public List<VideojuegoEntity> getAll(){
        return videojuegoRepository.findAll();
    }

    // Método para obtener los mejores videojuegos (por calificación)
    public List<VideojuegoEntity> obtenerMejoresVideojuegos() {
        Pageable topPageable = PageRequest.of(0, 2); // Top 5 videojuegos
        return videojuegoRepository.findTopRatedGames(topPageable);
    }

    // Método para obtener los peores videojuegos (por calificación)
    public List<VideojuegoEntity> obtenerPeoresVideojuegos() {
        Pageable bottomPageable = PageRequest.of(0, 2); // Bottom 5 videojuegos
        return videojuegoRepository.findLowestRatedGames(bottomPageable);
    }
}
