package com.example.obligatorio2.EntitiesDTOs;

import com.example.obligatorio2.Entity.TarjetaEntity;

public class TarjetaDTO {
    private int id;
    private String emisor;
    private String numero;
    private String tipo;

    public TarjetaDTO(TarjetaEntity tarjeta) {
        this.id = tarjeta.getId();
        this.emisor = tarjeta.getNombreTarjeta();
        this.numero = tarjeta.getNumero().substring(tarjeta.getNumero().length() - 4);
        this.tipo = tarjeta.getTipo();
    }
}

