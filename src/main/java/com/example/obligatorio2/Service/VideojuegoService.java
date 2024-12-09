package com.example.obligatorio2.Service;


import com.example.obligatorio2.Entity.VideojuegoEntity;

import java.util.List;

public interface VideojuegoService {
    public VideojuegoEntity save(VideojuegoEntity videojuegoEntity);
    public String delete(int id);
    public VideojuegoEntity getById(int id);
    public List<VideojuegoEntity> getAll();
    public List<VideojuegoEntity> obtenerMejoresVideojuegos();
    public List<VideojuegoEntity> obtenerPeoresVideojuegos();
}
