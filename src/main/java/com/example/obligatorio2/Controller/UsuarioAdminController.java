package com.example.obligatorio2.Controller;

import com.example.obligatorio2.Entity.GeneroEntity;
import com.example.obligatorio2.Entity.UsuarioAdminEntity;
import com.example.obligatorio2.Entity.UsuarioEntity;
import com.example.obligatorio2.Service.GeneroService;
import com.example.obligatorio2.Service.UsuarioAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/usuariosAdmin")
public class UsuarioAdminController {

    @Autowired
    private UsuarioAdminService usuarioAdminService;

    @PostMapping("/addUsuarioAdmin")
    public ResponseEntity<UsuarioAdminEntity> addUsuarioAdmin(@RequestBody UsuarioAdminEntity usuarioAdmin){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioAdminService.save(usuarioAdmin));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioAdminEntity>> getUsuariosAdmin(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioAdminService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioAdminEntity> getUsuarioAdmin(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioAdminService.getById(id));
    }

    @PutMapping
    public ResponseEntity<?> updateUsuarioAdmin(@RequestBody UsuarioAdminEntity usuarioAdmin){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioAdminService.save(usuarioAdmin));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUsuarioAdmin(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioAdminService.delete(id));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioAdminEntity usuarioAdminEntity){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioAdminService.login(usuarioAdminEntity));
    }
}
