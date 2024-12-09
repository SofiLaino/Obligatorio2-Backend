package com.example.obligatorio2.Respository;

import com.example.obligatorio2.Entity.UsuarioAdminEntity;
import com.example.obligatorio2.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioAdminRepository extends JpaRepository<UsuarioAdminEntity, Integer> {
    List<UsuarioAdminEntity> findByEmailAndPassword(String email, String password);
}
