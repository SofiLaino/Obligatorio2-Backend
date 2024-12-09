package com.example.obligatorio2.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tarjeta")
public class TarjetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombreTarjeta;

    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false)
    private String fechaVencimiento;

    @Column(nullable = false)
    private String CVV;

    @Column(nullable = false)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    @JsonBackReference
    private UsuarioEntity usuario;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    // Constructor vacío
    public TarjetaEntity() {
    }

    // Constructor con parámetros
    public TarjetaEntity(String nombreTarjeta, String numero, String fechaVencimiento, String CVV, String tipo, UsuarioEntity usuario) {
        this.nombreTarjeta = nombreTarjeta;
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.CVV = CVV;
        this.tipo = tipo;
        this.usuario = usuario;
    }
}

