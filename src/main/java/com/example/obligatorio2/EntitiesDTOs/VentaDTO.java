package com.example.obligatorio2.EntitiesDTOs;

import com.example.obligatorio2.Entity.UsuarioEntity;

import java.sql.Date;
import java.util.List;

/**
 * DTO (Data Transfer Object) para representar una venta.
 */
public class VentaDTO {

    private int id;                                    // ID de la venta
    private Date fechaCompra;                         // Fecha en la que se realizó la venta
    private UsuarioEntity usuario;                    // Usuario que realizó la compra
    private List<VideojuegoVentaDTO> videojuegoVentas; // Lista de videojuegos vendidos en la venta

    /**
     * Constructor completo para el DTO de Venta.
     *
     * @param id               ID de la venta.
     * @param fechaCompra      Fecha de la compra.
     * @param usuario          Usuario que realizó la compra.
     * @param videojuegoVentas Lista de videojuegos vendidos en la venta.
     */
    public VentaDTO(int id, Date fechaCompra, UsuarioEntity usuario, List<VideojuegoVentaDTO> videojuegoVentas) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.usuario = usuario;
        this.videojuegoVentas = videojuegoVentas;
    }

    // Getters

    public int getId() {
        return id;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }


    public List<VideojuegoVentaDTO> getVideojuegoVentas() {
        return videojuegoVentas;
    }

    // Setters opcionales si los necesitas

    public void setId(int id) {
        this.id = id;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }


    public void setVideojuegoVentas(List<VideojuegoVentaDTO> videojuegoVentas) {
        this.videojuegoVentas = videojuegoVentas;
    }
}