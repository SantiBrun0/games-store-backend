package com.santiagobruno.gsbackend.service;

import com.santiagobruno.gsbackend.exceptions.ResourceNotFoundException;
import com.santiagobruno.gsbackend.model.Console;
import com.santiagobruno.gsbackend.repository.ConsoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ConsoleService {

    private final ConsoleRepository repository;

    public void save(Console console) {
        repository.save(console);
    }

    public List<Console> findAll() {
        return repository.findAll();
    }

    public Console findByName(String name) {
        return repository.findByName(name);
    }

    public void resupply(int id, Integer resupply) throws ResourceNotFoundException {
        Optional<Console> consola = repository.findById(id);
        if (consola.isPresent()) {
            Console newConsole = consola.get();
            newConsole.setStock(newConsole.getStock() + resupply);
            repository.save(newConsole);
        } else {
            throw new ResourceNotFoundException("La consola que desea modificar no existe");
        }
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public Optional<Console> getById(int id) {
        return repository.findById(id);
    }

}
