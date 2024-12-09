package com.example.obligatorio2.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "videojuego")

public class VideojuegoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private int precio;

    @Column
    private String imagenURL;

    @Column
    private int stock;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    @JsonManagedReference
    private GeneroEntity genero;

    @OneToMany(mappedBy = "videojuego")
    @JsonManagedReference
    private List<ResenaEntity> resenas;

    @ManyToOne
    @JoinColumn(name = "usuarioAdmin_id")
    private UsuarioAdminEntity usuarioAdmin;

    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<VideojuegoVentaEntity> videojuegosVendidos = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public GeneroEntity getGenero() {
        return genero;
    }

    public void setGenero(GeneroEntity genero) {
        this.genero = genero;
    }

    public UsuarioAdminEntity getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public List<ResenaEntity> getResenas() {
        return resenas;
    }

    public void setResenas(List<ResenaEntity> resenas) {
        this.resenas = resenas;
    }

    public void setUsuarioAdmin(UsuarioAdminEntity usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    }

    public List<VideojuegoVentaEntity> getVideojuegosVendidos() {
        return videojuegosVendidos;
    }

    public void setVideojuegosVendidos(List<VideojuegoVentaEntity> videojuegosVendidos) {
        this.videojuegosVendidos = videojuegosVendidos;
    }

    public VideojuegoEntity(int id, String nombre, String descripcion, int precio, String imagenURL, int stock, GeneroEntity genero, List<ResenaEntity> resenas, UsuarioAdminEntity usuarioAdmin, List<VideojuegoVentaEntity> videojuegosVendidos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenURL = imagenURL;
        this.stock = stock;
        this.genero = genero;
        this.resenas = resenas;
        this.usuarioAdmin = usuarioAdmin;
        this.videojuegosVendidos = videojuegosVendidos;
    }

    public VideojuegoEntity(){

    }
}
