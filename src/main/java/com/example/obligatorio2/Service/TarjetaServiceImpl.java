package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.TarjetaEntity;
import com.example.obligatorio2.Excepetions.BadRequestException;
import com.example.obligatorio2.Excepetions.EntityNotFoundException;
import com.example.obligatorio2.Respository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Override
    public TarjetaEntity save(TarjetaEntity tarjetaEntity) {
        // Validaciones
        if (!tarjetaEntity.getNumero().matches("\\d{13,19}")) {
            throw new BadRequestException("El número de tarjeta debe tener entre 13 y 19 dígitos.");
        }
        if (!tarjetaEntity.getCVV().matches("\\d{3}")) {
            throw new BadRequestException("El CVV debe tener exactamente 3 dígitos.");
        }

        // Guardar la tarjeta
        return tarjetaRepository.save(tarjetaEntity);
    }

    @Override
    public String delete(int id) {
        if (tarjetaRepository.existsById(id)) {
            tarjetaRepository.deleteById(id);
            return "Tarjeta eliminada";
        }
        throw new BadRequestException("La tarjeta con id " + id + " no existe");
    }

    @Override
    public TarjetaEntity getById(int id) {
        return tarjetaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarjeta no encontrada"));
    }

    @Override
    public List<TarjetaEntity> getAll() {
        return tarjetaRepository.findAll();
    }

    @Override
    public List<TarjetaEntity> getByUsuarioId(int usuarioId) {
        return List.of();
    }
}