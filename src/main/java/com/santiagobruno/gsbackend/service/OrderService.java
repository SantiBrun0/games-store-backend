package com.santiagobruno.gsbackend.service;

import com.santiagobruno.gsbackend.model.Order;
import com.santiagobruno.gsbackend.model.OrderDTO;
import com.santiagobruno.gsbackend.model.Videogame;
import com.santiagobruno.gsbackend.repository.OrderRepository;
import com.santiagobruno.gsbackend.repository.VideogameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository repository;

    private final VideogameRepository videogameRepository;

    public String save(OrderDTO order) {
        Order newOrder = new Order();
        List<Videogame> videogames = new ArrayList<>();

        for (int id: order.videogames()) {
            var videogame = videogameRepository.findByVideogameId(id);
            videogames.add(videogame);
        }

        var trackingCode = UUID.randomUUID().toString();

        newOrder.setVideogames(videogames);
        newOrder.setDate(order.date());
        newOrder.setFullNameCustomer(order.fullNameCustomer());
        newOrder.setDni(order.dni());
        newOrder.setPhoneNumber(order.phoneNumber());
        newOrder.setAddress(order.address());
        newOrder.setMount(order.mount());
        newOrder.setTrackingCode(trackingCode);

        repository.save(newOrder);

        return trackingCode;
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findByTrackingCode(String trackingCode) {
        return repository.findByTrackingCode(trackingCode);
    }

}
