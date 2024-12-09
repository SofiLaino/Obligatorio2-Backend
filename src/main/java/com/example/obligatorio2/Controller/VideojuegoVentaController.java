package com.example.obligatorio2.Controller;

import com.example.obligatorio2.Entity.VentaEntity;
import com.example.obligatorio2.Entity.VideojuegoEntity;
import com.example.obligatorio2.Entity.VideojuegoVentaEntity;
import com.example.obligatorio2.Respository.VideojuegoVentaRepository;
import com.example.obligatorio2.Service.VentaService;
import com.example.obligatorio2.Service.VideojuegoService;
import com.example.obligatorio2.Service.VideojuegoVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/videojuegoVenta")
public class VideojuegoVentaController {

    @Autowired
    VideojuegoVentaService videojuegoVentaService;
    @Autowired
    VideojuegoService videojuegoService;
    @Autowired
    VentaService ventaService;

    @GetMapping
    public List<VideojuegoVentaEntity> getAll() {
        return videojuegoVentaService.getAll();
    }

    @PostMapping
    public VideojuegoVentaEntity save(@RequestBody Map<String, Object> requestBody) {
        int videojuego_id = ((Number) requestBody.get("videojuego_id")).intValue();
        int venta_id = ((Number) requestBody.get("venta_id")).intValue();
        int cantidad = ((Number) requestBody.get("cantidad")).intValue();

        VideojuegoEntity videojuego = videojuegoService.getById(videojuego_id);
        VentaEntity venta = ventaService.getById(venta_id);

        VideojuegoVentaEntity videojuegoVenta = new VideojuegoVentaEntity();
        videojuegoVenta.setVideojuego(videojuego);
        videojuegoVenta.setVenta(venta);
        videojuegoVenta.setCantidad(cantidad);

        return videojuegoVentaService.save(videojuegoVenta);
    }

}
