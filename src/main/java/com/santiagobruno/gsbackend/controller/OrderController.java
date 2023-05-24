package com.santiagobruno.gsbackend.controller;

import com.santiagobruno.gsbackend.exceptions.ResourceNotFoundException;
import com.santiagobruno.gsbackend.model.Order;
import com.santiagobruno.gsbackend.model.OrderDTO;
import com.santiagobruno.gsbackend.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@CrossOrigin
public class OrderController {

    private final OrderService service;

    @GetMapping("/order")
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/order/{trackingCode}")
    public ResponseEntity<Order> findByName(@PathVariable String trackingCode) throws ResourceNotFoundException {
        Order order = service.findByTrackingCode(trackingCode);
        if (order == null) throw new ResourceNotFoundException("Orden no encontrada");
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<Map<String, String>> save(@RequestBody OrderDTO order) {
        var trackingCode = service.save(order);
        Map<String, String> response = new HashMap<>();
        response.put("message", "La orden se realizó con éxito");
        response.put("trackingCode", trackingCode);
        return ResponseEntity.ok(response);
    }

}
