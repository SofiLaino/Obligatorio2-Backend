package com.example.obligatorio2.Controller;

import com.example.obligatorio2.Entity.MembresiaEntity;
import com.example.obligatorio2.Service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/membresias")
public class MembresiaController {
    @Autowired
    private MembresiaService membresiaService;

    @PostMapping("/addMembresia")
    public ResponseEntity<MembresiaEntity> addMembresia(@RequestBody MembresiaEntity membresia){
        membresia.setFechaMembresia(new java.sql.Date(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.CREATED).body(membresiaService.save(membresia));
    }

    @GetMapping
    public ResponseEntity<List<MembresiaEntity>> getMembresias(){
        return ResponseEntity.status(HttpStatus.OK).body(membresiaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembresiaEntity> getMembresia(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(membresiaService.getById(id));
    }

    @PutMapping
    public ResponseEntity<?> updateMembresia(@RequestBody MembresiaEntity membresia){
        return ResponseEntity.status(HttpStatus.OK).body(membresiaService.save(membresia));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMembresia(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(membresiaService.delete(id));
    }
}
