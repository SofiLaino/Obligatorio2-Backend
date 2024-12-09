package com.example.obligatorio2.Respository;

import com.example.obligatorio2.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    List<UsuarioEntity> findByEmailAndPassword(String email, String password);
    // Filtrar usuarios con membresía (premium)
    @Query("SELECT u FROM UsuarioEntity u WHERE u.membresia IS NOT NULL")
    List<UsuarioEntity> findPremiumUsers();

    // Filtrar usuarios sin membresía (regulares)
    @Query("SELECT u FROM UsuarioEntity u WHERE u.membresia IS NULL")
    List<UsuarioEntity> findRegularUsers();
}
