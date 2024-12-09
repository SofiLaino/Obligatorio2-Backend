
package com.example.obligatorio2.Controller;

import com.example.obligatorio2.Entity.TarjetaEntity;
import com.example.obligatorio2.Service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tarjetas")
public class TarjetaController {
    @Autowired
    private TarjetaService tarjetaService;

    @PostMapping("/addTarjeta")
    public ResponseEntity<TarjetaEntity> addTarjeta(@RequestBody TarjetaEntity tarjeta){
        return ResponseEntity.status(HttpStatus.CREATED).body(tarjetaService.save(tarjeta));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TarjetaEntity>> getTarjetasPorUsuario(@PathVariable int usuarioId) {
        try {
            List<TarjetaEntity> tarjetas = tarjetaService.getByUsuarioId(usuarioId);
            return ResponseEntity.status(HttpStatus.OK).body(tarjetas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<TarjetaEntity>> getTarjeta(){
        return ResponseEntity.status(HttpStatus.OK).body(tarjetaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarjetaEntity> getTarjeta(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(tarjetaService.getById(id));
    }

    @PutMapping
    public ResponseEntity<?> updateTarjeta(@RequestBody TarjetaEntity tarjeta){
        return ResponseEntity.status(HttpStatus.OK).body(tarjetaService.save(tarjeta));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTarjeta(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(tarjetaService.delete(id));
    }
}
