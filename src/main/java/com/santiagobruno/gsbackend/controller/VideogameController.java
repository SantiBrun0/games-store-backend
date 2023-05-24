package com.santiagobruno.gsbackend.controller;


import com.santiagobruno.gsbackend.exceptions.ResourceNotFoundException;
import com.santiagobruno.gsbackend.model.Videogame;
import com.santiagobruno.gsbackend.model.VideogameDTO;
import com.santiagobruno.gsbackend.service.VideogameService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@CrossOrigin
public class VideogameController {

    private final VideogameService service;

    @GetMapping("/videogame")
    public ResponseEntity<List<Videogame>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/videogame/name/{name}")
    public ResponseEntity<List<Videogame>> findByName(@PathVariable String name) throws ResourceNotFoundException {
        List<Videogame> videogames = service.findByName(name);
        if (videogames.isEmpty()) throw new ResourceNotFoundException("Videojuego no encontrado");
        return new ResponseEntity<>(videogames, HttpStatus.OK);
    }

    @GetMapping("/videogame/console/{console}")
    public ResponseEntity<List<Videogame>> findByConsole(@PathVariable String console) throws ResourceNotFoundException {
        List<Videogame> videogames = service.findByConsole(console);
        if (videogames.isEmpty()) throw new ResourceNotFoundException("Videojuego no encontrado");
        return new ResponseEntity<>(videogames, HttpStatus.OK);
    }

    @GetMapping("/videogame/console/{console}/page/{page}")
    public ResponseEntity<Page<Videogame>> findByConsoleByPage(@PathVariable int page, @PathVariable String console) {
        Page<Videogame> videogames = service.findByConsoleByPage(console, page);
        return new ResponseEntity<>(videogames, HttpStatus.OK);
    }


    @GetMapping("/videogame/page/{page}")
    public ResponseEntity<Page<Videogame>> findByPage(@PathVariable int page) {
        Page<Videogame> videogames = service.findByPage(page);
        return new ResponseEntity<>(videogames, HttpStatus.OK);
    }

    @GetMapping("videogame/random")
    public ResponseEntity<List<Videogame>> findRandom() throws ResourceNotFoundException {
        List<Videogame> videogames = service.findRandom();
        if (videogames.isEmpty()) throw new ResourceNotFoundException("No hay videojuegos");
        return new ResponseEntity<>(videogames, HttpStatus.OK);
    }

    @PostMapping("/videogame")
    public ResponseEntity<Map<String, String>> save(@RequestBody VideogameDTO videogame) {
        service.save(videogame);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Se agregó el videojuego a la base de datos");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/videogame/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) throws ResourceNotFoundException {
        service.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Se elimino el videojuego de la base de datos");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/videogame/resupply")
    public ResponseEntity<Map<String, String>> resupply(@RequestParam int id, @RequestParam Integer resupply) throws ResourceNotFoundException {
        service.resupply(id, resupply);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Stock del videojuego reabastecido");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/videogame/sell")
    public ResponseEntity<Map<String, String>> sellVideogame(@RequestParam int id, @RequestParam Integer sale) throws ResourceNotFoundException {
        service.sellVideogame(id, sale);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Se realizó la venta del videojuego");
        return ResponseEntity.ok(response);
    }

}
