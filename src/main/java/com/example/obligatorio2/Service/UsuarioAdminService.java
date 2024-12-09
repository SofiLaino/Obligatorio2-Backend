package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.UsuarioAdminEntity;

import java.util.List;

public interface UsuarioAdminService {
    public UsuarioAdminEntity save(UsuarioAdminEntity usuarioAdminEntity);
    public String delete(int id);
    public UsuarioAdminEntity getById(int id);
    public List<UsuarioAdminEntity> getAll();
    public String login(UsuarioAdminEntity usuarioAdminEntity);
}
