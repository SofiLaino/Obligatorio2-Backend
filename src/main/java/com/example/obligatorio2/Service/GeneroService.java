package com.example.obligatorio2.Service;


import com.example.obligatorio2.Entity.GeneroEntity;

import java.util.List;

public interface GeneroService {
    public GeneroEntity save(GeneroEntity generoEntity);
    public String delete(int id);
    public GeneroEntity getById(int id);
    public List<GeneroEntity> getAll();
}
