package com.example.obligatorio2.Controller;

import com.example.obligatorio2.Entity.GeneroEntity;
import com.example.obligatorio2.Entity.UsuarioAdminEntity;
import com.example.obligatorio2.Entity.VideojuegoEntity;
import com.example.obligatorio2.Service.GeneroService;
import com.example.obligatorio2.Service.UsuarioAdminService;
import com.example.obligatorio2.Service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/videojuegos")
public class VideojuegoController {
    @Autowired
    private VideojuegoService videojuegoService;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private UsuarioAdminService usuarioAdminService;

    @PostMapping("/addVideojuego")
    public ResponseEntity<?> addVideojuego(@RequestBody Map<String, Object> requestData) {
        VideojuegoEntity videojuego = new VideojuegoEntity();
        videojuego.setNombre((String) requestData.get("nombre"));
        videojuego.setDescripcion((String) requestData.get("descripcion"));
        videojuego.setPrecio((int) requestData.get("precio"));
        videojuego.setImagenURL((String) requestData.get("imagenURL"));
        videojuego.setStock((Integer) requestData.get("stock"));

        Integer generoId = (Integer) requestData.get("generoId");
        Integer usuarioAdminId = (Integer) requestData.get("usuarioAdminId");

        if (generoId != null) {
            GeneroEntity genero = generoService.getById(generoId);
            videojuego.setGenero(genero);
        }
        if (usuarioAdminId != null) {
            UsuarioAdminEntity usuarioAdmin = usuarioAdminService.getById(usuarioAdminId);
            videojuego.setUsuarioAdmin(usuarioAdmin);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(videojuegoService.save(videojuego));
    }

    // Obtener todos los videojuegos
    @GetMapping
    public ResponseEntity<List<VideojuegoEntity>> getVideojuegos() {
        return ResponseEntity.status(HttpStatus.OK).body(videojuegoService.getAll());
    }

    // Obtener un videojuego por ID
    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoEntity> getVideojuego(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(videojuegoService.getById(id));
    }

    // Actualizar un videojuego
    // Actualizar un videojuego
    @PutMapping
    public ResponseEntity<?> updateVideojuego(@RequestBody Map<String, Object> requestData) {
        Integer videojuegoId = (Integer) requestData.get("id");
        VideojuegoEntity videojuego = videojuegoService.getById(videojuegoId);

        if (videojuego == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Videojuego no encontrado");
        }

        videojuego.setNombre((String) requestData.get("nombre"));
        videojuego.setDescripcion((String) requestData.get("descripcion"));
        videojuego.setPrecio((int) requestData.get("precio"));
        videojuego.setImagenURL((String) requestData.get("imagenURL"));
        videojuego.setStock((Integer) requestData.get("stock"));

        Integer generoId = (Integer) requestData.get("generoId");
        Integer usuarioAdminId = (Integer) requestData.get("usuarioAdminId");

        if (generoId != null) {
            GeneroEntity genero = generoService.getById(generoId);
            if (genero == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Género no encontrado");
            }
            videojuego.setGenero(genero);
        }

        if (usuarioAdminId != null) {
            UsuarioAdminEntity usuarioAdmin = usuarioAdminService.getById(usuarioAdminId);
            if (usuarioAdmin == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario administrador no encontrado");
            }
            videojuego.setUsuarioAdmin(usuarioAdmin);
        }

        return ResponseEntity.status(HttpStatus.OK).body(videojuegoService.save(videojuego));
    }

    // Actualizar el stock de un videojuego
    @PatchMapping("/{id}/stock")
    public ResponseEntity<?> actualizarStock(@PathVariable int id, @RequestBody Map<String, Integer> request) {
        // Verificar si el videojuego existe
        VideojuegoEntity videojuego = videojuegoService.getById(id);
        if (videojuego == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Videojuego no encontrado");
        }

        // Verificar que se pase una cantidad válida en el body
        Integer cantidad = request.get("cantidad");
        if (cantidad == null || cantidad <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cantidad no válida");
        }

        // Verificar que haya suficiente stock
        if (videojuego.getStock() < cantidad) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock insuficiente");
        }

        // Reducir el stock
        videojuego.setStock(videojuego.getStock() - cantidad);
        videojuegoService.save(videojuego);

        return ResponseEntity.status(HttpStatus.OK).body("Stock actualizado correctamente");
    }

    // Eliminar un videojuego
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVideojuego(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(videojuegoService.delete(id));
    }

    // Endpoint para obtener los mejores videojuegos (top 5)
    @GetMapping("/mejores")
    public List<VideojuegoEntity> obtenerMejoresVideojuegos() {
        return videojuegoService.obtenerMejoresVideojuegos();
    }

    // Endpoint para obtener los peores videojuegos (bottom 5)
    @GetMapping("/peores")
    public List<VideojuegoEntity> obtenerPeoresVideojuegos() {
        return videojuegoService.obtenerPeoresVideojuegos();
    }

}
