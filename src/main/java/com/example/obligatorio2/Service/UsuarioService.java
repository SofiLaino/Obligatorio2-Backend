package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService {
    UsuarioEntity save(UsuarioEntity usuarioEntity); // Guarda un usuario
    String delete(int id); // Elimina un usuario por ID
    UsuarioEntity getById(int id); // Obtiene un usuario por ID
    List<UsuarioEntity> getAll(); // Obtiene todos los usuarios
    UsuarioEntity login(UsuarioEntity usuarioEntity); // Realiza el login
}
