package com.example.obligatorio2.Controller;

import com.example.obligatorio2.EntitiesDTOs.VentaDTO;
import com.example.obligatorio2.Entity.UsuarioEntity;
import com.example.obligatorio2.Entity.VentaEntity;
import com.example.obligatorio2.Entity.VideojuegoVentaEntity;
import com.example.obligatorio2.Service.UsuarioService;
import com.example.obligatorio2.Service.VentaService;
import com.example.obligatorio2.Service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private VideojuegoService videojuegoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/addVenta")
    public ResponseEntity<?> addVenta(@RequestBody VentaDTO ventaDTO) {
        try {
            // Validar que el usuario exista
            UsuarioEntity usuario = usuarioService.getById(ventaDTO.getUsuario().getId());
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado.");
            }

            // Crear la entidad de Venta
            VentaEntity ventaEntity = new VentaEntity();
            ventaEntity.setUsuario(usuario);
            ventaEntity.setFechaCompra(new java.sql.Date(System.currentTimeMillis()));

            // Mapear videojuegos vendidos
            List<VideojuegoVentaEntity> videojuegosVendidos = ventaDTO.getVideojuegoVentas().stream().map(v -> {
                var videojuego = videojuegoService.getById(v.getId());
                if (videojuego == null) {
                    throw new IllegalArgumentException("El videojuego con ID " + v.getId() + " no existe.");
                }

                // Crear la relación VideojuegoVentaEntity
                VideojuegoVentaEntity vendido = new VideojuegoVentaEntity();
                vendido.setVideojuego(videojuego);
                vendido.setCantidad(v.getCantidad());
                vendido.setVenta(ventaEntity); // Relación bidireccional
                return vendido;
            }).collect(Collectors.toList());

            // Asociar videojuegos vendidos a la venta
            ventaEntity.setVideojuegosVendidos(videojuegosVendidos);

            // Guardar la venta
            VentaEntity savedVenta = ventaService.save(ventaEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedVenta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la venta.");
        }
    }

    @GetMapping
    public ResponseEntity<List<VentaDTO>> getVentas() {
        try {
            List<VentaDTO> ventas = ventaService.getAllVentasDTO();
            return ResponseEntity.status(HttpStatus.OK).body(ventas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVenta(@PathVariable int id) {
        try {
            VentaDTO venta = ventaService.getVentaDTOById(id);
            return ResponseEntity.status(HttpStatus.OK).body(venta);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada.");
        }
    }

    @GetMapping("/porFecha")
    public ResponseEntity<?> getVentasPorFecha(
            @RequestParam("fechaInicio") Date fechaInicio,
            @RequestParam("fechafin") Date fechaFin) {
        try {
            List<VentaDTO> ventas = ventaService.getVentasPorFecha(fechaInicio, fechaFin);
            return ResponseEntity.status(HttpStatus.OK).body(ventas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener las ventas por fecha.");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateVenta(@RequestBody VentaEntity venta) {
        try {
            VentaEntity updatedVenta = ventaService.save(venta);
            return ResponseEntity.status(HttpStatus.OK).body(updatedVenta);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la venta.");
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable int id) {
        try {
            String result = ventaService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar la venta.");
        }
    }
}