package com.example.obligatorio2.Controller;

import com.example.obligatorio2.EntitiesDTOs.VentaDTO;
import com.example.obligatorio2.Entity.MembresiaEntity;
import com.example.obligatorio2.Entity.UsuarioEntity;
import com.example.obligatorio2.Respository.UsuarioRepository;
import com.example.obligatorio2.Service.MembresiaService;
import com.example.obligatorio2.Service.UsuarioService;
import com.example.obligatorio2.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MembresiaService membresiaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VentaService ventaService;


    @PostMapping("/addUsuario")
    public ResponseEntity<?> addUsuario(@RequestBody Map<String, Object> requestData) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail((String) requestData.get("email"));
        usuario.setPassword((String) requestData.get("password"));
        usuario.setNombre((String) requestData.get("nombre"));
        usuario.setApellido((String) requestData.get("apellido"));
        usuario.setFechaRegistro(new java.sql.Date(System.currentTimeMillis()));

        Integer membresiaId = (Integer) requestData.get("membresiaId");
        if (membresiaId != null) {
            MembresiaEntity membresia = membresiaService.getById(membresiaId);
            usuario.setMembresia(membresia);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/{usuarioId}/membresia/{membresiaId}")
    public ResponseEntity<?> asociarMembresiaAUsuario(@PathVariable int usuarioId, @PathVariable int membresiaId) {
        UsuarioEntity usuario = usuarioService.getById(usuarioId);
        MembresiaEntity membresia = membresiaService.getById(membresiaId);
        usuario.setMembresia(membresia);
        UsuarioEntity usuarioActualizado = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioActualizado);
    }


    @PutMapping("/{usuarioId}/desvincularMembresia")
    public ResponseEntity<?> desvincularMembresia(@PathVariable int usuarioId) {
        UsuarioEntity usuario = usuarioService.getById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        usuario.setMembresia(null);

        // Guardar los cambios en la base de datos
        UsuarioEntity usuarioActualizado = usuarioService.save(usuario);

        // Retornar respuesta
        return ResponseEntity.status(HttpStatus.OK).body(usuarioActualizado);
    }

    @GetMapping("/{usuarioId}/ventas")
    public ResponseEntity<List<VentaDTO>> getVentasPorUsuario(@PathVariable int usuarioId) {
        try {
            List<VentaDTO> ventas = ventaService.getVentasPorUsuario(usuarioId);
            if (ventas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Sin ventas para este usuario
            }
            return ResponseEntity.ok(ventas); // Retorna las ventas en formato JSON
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping
    public ResponseEntity<?> updateUsuario(@RequestBody UsuarioEntity usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getById(id));
    }

    @GetMapping
    public List<Map<String, Object>> getUsuarios() {
        return usuarioRepository.findAll().stream().map(usuario -> {
            Map<String, Object> usuarioMap = new HashMap<>();
            usuarioMap.put("id", usuario.getId());
            usuarioMap.put("nombre", usuario.getNombre());
            usuarioMap.put("apellido", usuario.getApellido());
            usuarioMap.put("email", usuario.getEmail());
            usuarioMap.put("password", usuario.getPassword());
            usuarioMap.put("tipo", usuario.getMembresia() == null ? "regular" : "premium");
            usuarioMap.put("fechaRegistro", usuario.getFechaRegistro());
            return usuarioMap;
        }).collect(Collectors.toList());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioEntity usuarioEntity) {
        UsuarioEntity usuarioAutenticado = usuarioService.login(usuarioEntity);

        Map<String, Object> response = new HashMap<>();
        response.put("id", usuarioAutenticado.getId());
        response.put("nombre", usuarioAutenticado.getNombre());
        response.put("email", usuarioAutenticado.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/premium")
    public List<Map<String, Object>> getUsuariosPremium() {
        return usuarioRepository.findPremiumUsers().stream().map(usuario -> {
            Map<String, Object> usuarioMap = new HashMap<>();
            usuarioMap.put("id", usuario.getId());
            usuarioMap.put("nombre", usuario.getNombre());
            usuarioMap.put("email", usuario.getEmail());
            usuarioMap.put("tipo", "premium");
            return usuarioMap;
        }).collect(Collectors.toList());
    }

    @GetMapping("/regular")
    public List<Map<String, Object>> getUsuariosRegulares() {
        return usuarioRepository.findRegularUsers().stream().map(usuario -> {
            Map<String, Object> usuarioMap = new HashMap<>();
            usuarioMap.put("id", usuario.getId());
            usuarioMap.put("nombre", usuario.getNombre());
            usuarioMap.put("email", usuario.getEmail());
            usuarioMap.put("tipo", "regular");
            return usuarioMap;
        }).collect(Collectors.toList());
    }
}
