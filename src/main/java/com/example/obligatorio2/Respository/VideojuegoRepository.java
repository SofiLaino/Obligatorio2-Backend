package com.example.obligatorio2.Respository;

import com.example.obligatorio2.Entity.VideojuegoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VideojuegoRepository  extends JpaRepository<VideojuegoEntity, Integer> {


    @Query("SELECT v FROM VideojuegoEntity v " +
            "JOIN v.resenas r " +
            "GROUP BY v " +
            "ORDER BY AVG(r.calificacion) DESC")
    List<VideojuegoEntity> findTopRatedGames(Pageable pageable);


    @Query("SELECT v FROM VideojuegoEntity v " +
            "JOIN v.resenas r " +
            "GROUP BY v " +
            "HAVING AVG(r.calificacion) > 0 " +  // Asegura que solo se incluyan videojuegos con al menos una reseña
            "ORDER BY AVG(r.calificacion) ASC")
    List<VideojuegoEntity> findLowestRatedGames(Pageable pageable);
}
