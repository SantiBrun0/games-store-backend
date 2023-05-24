package com.santiagobruno.gsbackend.service;

import com.santiagobruno.gsbackend.exceptions.ResourceNotFoundException;
import com.santiagobruno.gsbackend.model.Videogame;
import com.santiagobruno.gsbackend.model.VideogameDTO;
import com.santiagobruno.gsbackend.repository.ConsoleRepository;
import com.santiagobruno.gsbackend.repository.VideogameRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VideogameService {

    private final VideogameRepository repository;
    private final ConsoleRepository consoleRepository;

    public void save(VideogameDTO videogame) {
        Videogame newVideogame = new Videogame();

        newVideogame.setConsole(consoleRepository.findByName(videogame.console()));
        newVideogame.setName(videogame.name());
        newVideogame.setGenre(videogame.genre());
        newVideogame.setLaunch(videogame.launch());
        newVideogame.setStock(videogame.stock());
        newVideogame.setImages(videogame.images());
        newVideogame.setPrice(videogame.price());
        newVideogame.setDescription(videogame.description());

        repository.save(newVideogame);
    }

    public List<Videogame> findAll() {
        return repository.findAll();
    }

    public List<Videogame> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Videogame> findByConsole(String console) {
        return repository.findByConsole(console);
    }

    public Page<Videogame> findByPage(int page) {
        return repository.findAll(PageRequest.of(page, 4));
    }

    public Page<Videogame> findByConsoleByPage(String consoleName, int page) {
        Pageable pageable = PageRequest.of(page, 4);
        return repository.findByConsoleByPage(consoleName, pageable);
    }

    public List<Videogame> findRandom() {
        return repository.findRandom();
    }

    public void resupply(int id, Integer resupply) throws ResourceNotFoundException {
        Optional<Videogame> videogame = repository.findById(id);
        if (videogame.isPresent()) {
            Videogame newVideogame = videogame.get();
            newVideogame.setStock(newVideogame.getStock() + resupply);
            repository.save(newVideogame);
        } else {
            throw new ResourceNotFoundException("El videojuego que desea modificar no existe");
        }
    }

    public void sellVideogame(int id, Integer sale) throws ResourceNotFoundException {
        Optional<Videogame> videogame = repository.findById(id);
        if (videogame.isPresent()) {
            Videogame newVideogame = videogame.get();
            if (newVideogame.getStock() < sale) throw new ResourceNotFoundException("No hay suficiente stock para la venta");
            newVideogame.setStock(newVideogame.getStock() - sale);
            repository.save(newVideogame);
        } else {
            throw new ResourceNotFoundException("El videojuego que desea modificar no existe");
        }
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public Optional<Videogame> getById(int id) {
        return repository.findById(id);
    }

}
