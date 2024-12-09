package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.MembresiaEntity;
import com.example.obligatorio2.Excepetions.BadRequestException;
import com.example.obligatorio2.Excepetions.EntityNotFoundException;
import com.example.obligatorio2.Respository.MembresiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembresiaServiceImpl implements MembresiaService{

    @Autowired
    private MembresiaRepository membresiaRepository;

    public MembresiaEntity save(MembresiaEntity membresiaEntity){
        try {
            return membresiaRepository.save(membresiaEntity);

        }catch (Exception ex){
            throw new BadRequestException("Ya existe esa membresia");
        }
    }
    public String delete(int id){
        if(membresiaRepository.existsById(id)){
            membresiaRepository.deleteById(id);
            return "Membresia eliminada";
        }
        throw new BadRequestException("La membresia con id " + id +" no existe");
    }
    public MembresiaEntity getById(int id){
        return membresiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membresia no encontrada "));
    }
    public List<MembresiaEntity> getAll(){
        return membresiaRepository.findAll();
    }
}
