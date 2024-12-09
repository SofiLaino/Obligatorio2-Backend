package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.UsuarioEntity;
import com.example.obligatorio2.Respository.UsuarioRepository;
import com.example.obligatorio2.Excepetions.BadRequestException;
import com.example.obligatorio2.Excepetions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Guarda o actualiza un usuario.
     *
     * @param usuarioEntity Usuario a guardar o actualizar.
     * @return El usuario guardado.
     */
    @Override
    public UsuarioEntity save(UsuarioEntity usuarioEntity) {
        try {
            return usuarioRepository.save(usuarioEntity);
        } catch (Exception ex) {
            throw new BadRequestException("Error al guardar el usuario: " + ex.getMessage());
        }
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id ID del usuario a eliminar.
     * @return Mensaje de confirmación de eliminación.
     */
    @Override
    public String delete(int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Usuario eliminado exitosamente.";
        }
        throw new BadRequestException("El usuario con id " + id + " no existe.");
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id ID del usuario.
     * @return El usuario encontrado.
     */
    @Override
    public UsuarioEntity getById(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id));
    }

    /**
     * Obtiene una lista de todos los usuarios.
     *
     * @return Lista de usuarios.
     */
    @Override
    public List<UsuarioEntity> getAll() {
        return usuarioRepository.findAll();
    }

    /**
     * Autentica un usuario por email y contraseña.
     *
     * @param user Usuario con las credenciales.
     * @return El usuario autenticado.
     */
    @Override
    public UsuarioEntity login(UsuarioEntity user) {
        List<UsuarioEntity> usuarios = usuarioRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (!usuarios.isEmpty()) {
            return usuarios.get(0); // Devuelve el primer usuario encontrado.
        }
        throw new BadRequestException("Credenciales incorrectas.");
    }
}