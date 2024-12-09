package com.example.obligatorio2.EntitiesDTOs;

/**
 * DTO (Data Transfer Object) para representar la relación entre un videojuego y una venta.
 */
public class VideojuegoVentaDTO {

    private int id;                 // ID del videojuego
    private String nombre;          // Nombre del videojuego
    private double precio;          // Precio del videojuego
    private String imagenURL;       // URL de la imagen del videojuego
    private int cantidad;           // Cantidad de unidades vendidas

    /**
     * Constructor completo.
     *
     * @param id         ID del videojuego.
     * @param nombre     Nombre del videojuego.
     * @param precio     Precio del videojuego.
     * @param imagenURL  URL de la imagen del videojuego.
     * @param cantidad   Cantidad de unidades vendidas.
     */
    public VideojuegoVentaDTO(int id, String nombre, double precio, String imagenURL, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.imagenURL = imagenURL;
        this.cantidad = cantidad;
    }

    // Getters y Setters

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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
