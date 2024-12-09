package com.example.obligatorio2.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "venta_videojuego")
public class VideojuegoVentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    @JsonBackReference // Evita ciclos infinitos en la serialización JSON
    private VentaEntity venta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "videojuego_id", nullable = false)
    @JsonManagedReference // Permite incluir los detalles del videojuego en la serialización JSON
    private VideojuegoEntity videojuego;

    @Column(nullable = false)
    private int cantidad;

    // Constructor vacío requerido por JPA
    public VideojuegoVentaEntity() {}

    // Constructor con parámetros
    public VideojuegoVentaEntity(VentaEntity venta, VideojuegoEntity videojuego, int cantidad) {
        this.venta = venta;
        this.videojuego = videojuego;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VentaEntity getVenta() {
        return venta;
    }

    public void setVenta(VentaEntity venta) {
        this.venta = venta;
    }

    public VideojuegoEntity getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(VideojuegoEntity videojuego) {
        this.videojuego = videojuego;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}