package com.example.obligatorio2.Controller;

import com.example.obligatorio2.Entity.GeneroEntity;
import com.example.obligatorio2.Service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping("/addGenero")
    public ResponseEntity<GeneroEntity> addGenero(@RequestBody GeneroEntity genero){
        return ResponseEntity.status(HttpStatus.CREATED).body(generoService.save(genero));
    }

    @GetMapping
    public ResponseEntity<List<GeneroEntity>> getGeneros(){
        return ResponseEntity.status(HttpStatus.OK).body(generoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroEntity> getGenero(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(generoService.getById(id));
    }

    @PutMapping
    public ResponseEntity<?> updategeneros(@RequestBody GeneroEntity genero){
        return ResponseEntity.status(HttpStatus.OK).body(generoService.save(genero));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteGenero(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(generoService.delete(id));
    }

}
