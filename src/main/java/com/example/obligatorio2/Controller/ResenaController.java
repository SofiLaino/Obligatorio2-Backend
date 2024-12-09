package com.example.obligatorio2.Controller;

import com.example.obligatorio2.Entity.GeneroEntity;
import com.example.obligatorio2.Entity.ResenaEntity;
import com.example.obligatorio2.Entity.UsuarioEntity;
import com.example.obligatorio2.Entity.VideojuegoEntity;
import com.example.obligatorio2.Excepetions.BadRequestException;
import com.example.obligatorio2.Service.ResenaService;
import com.example.obligatorio2.Service.UsuarioService;
import com.example.obligatorio2.Service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/resenas")
public class ResenaController {
    @Autowired
    private ResenaService resenaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VideojuegoService videojuegoService;

    @PostMapping("/addResena")
    public ResponseEntity<?> addResena(@RequestBody Map<String, Object> requestData) {
        int usuarioId = Integer.parseInt(requestData.get("usuarioId").toString());
        int videojuegoId = Integer.parseInt(requestData.get("videojuegoId").toString());
        String resena = (String) requestData.get("resena");
        Integer calificacion = Integer.parseInt(requestData.get("calificacion").toString());

        // Crear la fecha actual
        Date fecha = new java.sql.Date(System.currentTimeMillis());

        try {
            // Pasar los datos correctamente al servicio
            ResenaEntity nuevaResena = resenaService.agregarResena(usuarioId, videojuegoId, resena, calificacion, fecha);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaResena);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<ResenaEntity>> getResena(){
        return ResponseEntity.status(HttpStatus.OK).body(resenaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaEntity> getResena(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(resenaService.getById(id));
    }

    // Endpoint para verificar si un usuario puede dejar una reseña
    @GetMapping("/puedeDejarResena")
    public ResponseEntity<?> puedeDejarResena(@RequestParam int usuarioId, @RequestParam int videojuegoId) {
        boolean puedeDejar = resenaService.puedeDejarResena(usuarioId, videojuegoId);
        if (puedeDejar) {
            return ResponseEntity.status(HttpStatus.OK).body("El usuario puede dejar una reseña.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El usuario no ha comprado este videojuego.");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateResena(@RequestBody Map<String, Object> requestData) {
        Integer resenaId = (Integer) requestData.get("id");
        ResenaEntity resena = resenaService.getById(resenaId); // Recupera la reseña existente

        if (resena == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reseña no encontrada");
        }

        resena.setResena((String) requestData.get("resena"));
        resena.setFecha(Date.valueOf((String) requestData.get("fecha")));
        resena.setCalificacion((Integer) requestData.get("calificacion"));

        Integer videojuegoId = (Integer) requestData.get("videojuegoId");
        Integer usuarioId = (Integer) requestData.get("usuarioId");

        if (videojuegoId != null) {
            VideojuegoEntity videojuego = videojuegoService.getById(videojuegoId);
            if (videojuego == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Videojuego no encontrado");
            }
            resena.setVideojuego(videojuego);
        }

        if (usuarioId != null) {
            UsuarioEntity usuario = usuarioService.getById(usuarioId);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
            }
            resena.setUsuario(usuario);
        }

        return ResponseEntity.status(HttpStatus.OK).body(resenaService.save(resena));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteResena(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(resenaService.delete(id));
    }
}
