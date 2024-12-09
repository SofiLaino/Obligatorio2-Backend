package com.example.obligatorio2.Service;

import com.example.obligatorio2.Entity.MembresiaEntity;
import java.util.List;

public interface MembresiaService {
    public MembresiaEntity save(MembresiaEntity membresiaEntity);
    public String delete(int id);
    public MembresiaEntity getById(int id);
    public List<MembresiaEntity> getAll();
}
