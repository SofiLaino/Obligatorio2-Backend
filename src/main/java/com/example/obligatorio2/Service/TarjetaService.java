package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.TarjetaEntity;

import java.util.List;

public interface TarjetaService {
    public TarjetaEntity save(TarjetaEntity tarjetaEntity);
    public String delete(int id);
    public TarjetaEntity getById(int id);
    public List<TarjetaEntity> getAll();

    List<TarjetaEntity> getByUsuarioId(int usuarioId);
}
