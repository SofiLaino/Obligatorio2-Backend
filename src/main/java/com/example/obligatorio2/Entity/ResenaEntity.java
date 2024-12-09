package com.example.obligatorio2.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "resena")
public class ResenaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String resena;

    @Column
    private Date fecha;

    @Column
    private int calificacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "videojuego_id")
    @JsonBackReference
    private VideojuegoEntity videojuego;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResena() {
        return resena;
    }

    public void setResena(String resena) {
        this.resena = resena;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public VideojuegoEntity getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(VideojuegoEntity videojuego) {
        this.videojuego = videojuego;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public ResenaEntity(int id, String resena, Date fecha, int calificacion, VideojuegoEntity videojuego, UsuarioEntity usuario) {
        this.id = id;
        this.resena = resena;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.videojuego = videojuego;
        this.usuario = usuario;
    }

    public ResenaEntity() {
    }
}
