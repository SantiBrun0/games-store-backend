package com.santiagobruno.gsbackend.controller;

import com.santiagobruno.gsbackend.exceptions.ResourceNotFoundException;
import com.santiagobruno.gsbackend.model.Console;
import com.santiagobruno.gsbackend.service.ConsoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@RestController
@CrossOrigin
public class ConsoleController {

    private final ConsoleService service;

    @GetMapping("/console")
    public ResponseEntity<List<Console>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/console/{name}")
    public ResponseEntity<Console> findByName(@PathVariable String name) throws ResourceNotFoundException {
        Console console = service.findByName(name);
        if (Objects.isNull(console)) throw new ResourceNotFoundException("Consola no encontrada");
        return new ResponseEntity<>(console, HttpStatus.OK);
    }

    @PostMapping("/console")
    public ResponseEntity<Map<String, String>> save(@RequestBody Console console) {
        Map<String, String> response = new HashMap<>();
        service.save(console);
        response.put("message", "Se agregó la consola a la base de datos");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/console/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) throws ResourceNotFoundException {
        service.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Se elimino la consola de la base de datos");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/console")
    public ResponseEntity<Map<String, String>> resupply(@RequestParam int id, @RequestParam Integer resupply) throws ResourceNotFoundException {
        service.resupply(id, resupply);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Se modifico el stock de la consola");
        return ResponseEntity.ok(response);
    }

}
