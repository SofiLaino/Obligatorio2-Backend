package com.example.obligatorio2.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "membresia")
public class MembresiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date fechaMembresia;

    @OneToMany(mappedBy = "membresia")
    @JsonBackReference
    private List<UsuarioEntity> usuarios;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaMembresia() {
        return fechaMembresia;
    }

    public void setFechaMembresia(Date fechaMembresia) {
        this.fechaMembresia = fechaMembresia;
    }

    public List<UsuarioEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioEntity> usuarios) {
        this.usuarios = usuarios;
    }

    public MembresiaEntity(int id, Date fechaMembresia, List<UsuarioEntity> usuarios) {
        this.id = id;
        this.fechaMembresia = fechaMembresia;
        this.usuarios = usuarios;
    }

    public MembresiaEntity() {
    }
}
