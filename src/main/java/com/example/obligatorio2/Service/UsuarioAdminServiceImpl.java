package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.UsuarioAdminEntity;
import com.example.obligatorio2.Excepetions.BadRequestException;
import com.example.obligatorio2.Excepetions.EntityNotFoundException;
import com.example.obligatorio2.Respository.UsuarioAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioAdminServiceImpl implements UsuarioAdminService{
    @Autowired
    private UsuarioAdminRepository usuarioAdminRepository;

    public UsuarioAdminEntity save(UsuarioAdminEntity usuarioAdminEntity){
        try {
            return usuarioAdminRepository.save(usuarioAdminEntity);

        }catch (Exception ex){
            throw new BadRequestException("Ya existe ese usuario");
        }
    }
    public String delete(int id){
        if(usuarioAdminRepository.existsById(id)){
            usuarioAdminRepository.deleteById(id);
            return "Usuario eliminado";
        }
        throw new BadRequestException("El usuario con id " + id +" no existe");
    }
    public UsuarioAdminEntity getById(int id){
        return usuarioAdminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado "));
    }
    public List<UsuarioAdminEntity> getAll(){
        return usuarioAdminRepository.findAll();
    }

    public String login(UsuarioAdminEntity usuarioAdmin){
        if(usuarioAdminRepository.findByEmailAndPassword(usuarioAdmin.getEmail(), usuarioAdmin.getPassword()).size()>0){
            return "Se inició sesión con éxito";
        }
        throw new BadRequestException("Credenciales incorrectas");
    }
}
