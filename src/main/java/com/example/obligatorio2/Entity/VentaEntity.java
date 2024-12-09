package com.example.obligatorio2.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venta")
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date fechaCompra;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Permite incluir los detalles de videojuegos en la serialización JSON
    private List<VideojuegoVentaEntity> videojuegosVendidos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference // Evita ciclos infinitos en la serialización JSON
    private UsuarioEntity usuario;

    // Constructor vacío requerido por JPA
    public VentaEntity() {
    }

    // Constructor con parámetros
    public VentaEntity(int id, Date fechaCompra, List<VideojuegoVentaEntity> videojuegosVendidos, UsuarioEntity usuario) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.videojuegosVendidos = videojuegosVendidos;
        this.usuario = usuario;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public List<VideojuegoVentaEntity> getVideojuegosVendidos() {
        return videojuegosVendidos;
    }

    public void setVideojuegosVendidos(List<VideojuegoVentaEntity> videojuegosVendidos) {
        this.videojuegosVendidos = videojuegosVendidos;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

}