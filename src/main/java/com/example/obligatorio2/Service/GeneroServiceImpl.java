package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.GeneroEntity;
import com.example.obligatorio2.Excepetions.BadRequestException;
import com.example.obligatorio2.Excepetions.EntityNotFoundException;
import com.example.obligatorio2.Respository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImpl implements GeneroService{
    
    @Autowired
    private GeneroRepository generoRepository;

    public GeneroEntity save(GeneroEntity generoEntity){
        try {
            return generoRepository.save(generoEntity);

        }catch (Exception ex){
            throw new BadRequestException("Ya existe ese genero");
        }
    }
    public String delete(int id){
        if(generoRepository.existsById(id)){
            generoRepository.deleteById(id);
            return "Genero eliminado";
        }
        throw new BadRequestException("El genero con id " + id +" no existe");
    }
    public GeneroEntity getById(int id){
        return generoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genero no encontrado "));
    }
    public List<GeneroEntity> getAll(){
        return generoRepository.findAll();
    }

}
