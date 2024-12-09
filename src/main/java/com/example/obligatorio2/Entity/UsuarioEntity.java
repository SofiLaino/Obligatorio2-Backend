package com.example.obligatorio2.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private Date fechaRegistro;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
    @JsonManagedReference
    private TarjetaEntity tarjeta;

    @ManyToOne
    @JoinColumn(name = "membresia_id", nullable = true)
    private MembresiaEntity membresia;

    @OneToMany(mappedBy = "usuario")
    private List<VentaEntity> ventas;

    @OneToMany(mappedBy = "usuario")
    private List<ResenaEntity> resenas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public MembresiaEntity getMembresia() {
        return membresia;
    }

    public void setMembresia(MembresiaEntity membresia) {
        this.membresia = membresia;
    }

    public UsuarioEntity(int id, String email, String password, String nombre, String apellido, Date fechaRegistro, MembresiaEntity membresia) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaRegistro = fechaRegistro;
        this.membresia = membresia;
    }

    public UsuarioEntity()
    {

    }
}