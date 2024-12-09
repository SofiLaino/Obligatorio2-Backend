package com.example.obligatorio2.Service;

import com.example.obligatorio2.EntitiesDTOs.VentaDTO;
import com.example.obligatorio2.EntitiesDTOs.VideojuegoVentaDTO;
import com.example.obligatorio2.Entity.VentaEntity;
import com.example.obligatorio2.Entity.VideojuegoVentaEntity;
import com.example.obligatorio2.Excepetions.BadRequestException;
import com.example.obligatorio2.Excepetions.EntityNotFoundException;
import com.example.obligatorio2.Respository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public VentaEntity save(VentaEntity ventaEntity) {
        try {
            return ventaRepository.save(ventaEntity);
        } catch (Exception ex) {
            throw new BadRequestException("Error al guardar la venta: " + ex.getMessage());
        }
    }

    @Override
    public String delete(int id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            return "Venta eliminada exitosamente";
        }
        throw new BadRequestException("La venta con id " + id + " no existe");
    }

    @Override
    public VentaEntity getById(int id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con id: " + id));
    }

    @Override
    public List<VentaDTO> getAllVentasDTO() {
        return ventaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<VentaDTO> getVentasPorUsuario(int usuarioId) {
        List<VentaEntity> ventas = ventaRepository.findByUsuarioId(usuarioId);
        return ventas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<VentaDTO> getVentasPorFecha(Date fechaInicio, Date fechaFin) {
        List<VentaEntity> ventas = ventaRepository.findByFechaCompraBetween(fechaInicio, fechaFin);
        return ventas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VentaDTO getVentaDTOById(int id) {
        VentaEntity venta = getById(id);
        return convertToDTO(venta);
    }

    /**
     * Convierte una entidad VentaEntity a su correspondiente DTO VentaDTO.
     *
     * @param venta La entidad VentaEntity a convertir.
     * @return El DTO VentaDTO convertido.
     */
    private VentaDTO convertToDTO(VentaEntity venta) {
        List<VideojuegoVentaDTO> videojuegoVentaDTOs = Optional.ofNullable(venta.getVideojuegosVendidos())
                .orElse(new ArrayList<>()) // Asegura que no sea nulo
                .stream()
                .map(this::convertVideojuegoVentaToDTO)
                .collect(Collectors.toList());

        return new VentaDTO(
                venta.getId(),
                venta.getFechaCompra(),
                venta.getUsuario(),
                videojuegoVentaDTOs
        );
    }

    /**
     * Convierte una entidad VideojuegoVentaEntity a su correspondiente DTO VideojuegoVentaDTO.
     *
     * @param videojuegoVenta La entidad VideojuegoVentaEntity a convertir.
     * @return El DTO VideojuegoVentaDTO convertido.
     */
    private VideojuegoVentaDTO convertVideojuegoVentaToDTO(VideojuegoVentaEntity videojuegoVenta) {
        return new VideojuegoVentaDTO(
                videojuegoVenta.getVideojuego().getId(),
                videojuegoVenta.getVideojuego().getNombre(),
                videojuegoVenta.getVideojuego().getPrecio(),
                videojuegoVenta.getVideojuego().getImagenURL(),
                videojuegoVenta.getCantidad()
        );
    }
}