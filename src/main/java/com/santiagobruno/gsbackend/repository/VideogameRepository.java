package com.santiagobruno.gsbackend.repository;

import com.santiagobruno.gsbackend.model.Videogame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VideogameRepository extends JpaRepository<Videogame, Integer> {

    @Query("SELECT v FROM Videogame v WHERE LOWER(v.name) LIKE %:name%")
    List<Videogame> findByName(String name);

    @Query("SELECT v FROM Videogame v WHERE v.id = :id")
    Videogame findByVideogameId(int id);

    @Query("SELECT v FROM Videogame v WHERE LOWER(v.console.name) LIKE %:console%")
    List<Videogame> findByConsole(String console);

    @Query("SELECT v FROM Videogame v WHERE LOWER(v.console.name) LIKE %:consoleName%")
    Page<Videogame> findByConsoleByPage(@Param("consoleName") String consoleName, Pageable pageable);

    @Query("SELECT v FROM Videogame v WHERE v.stock > 0 ORDER BY RAND() LIMIT 8")
    List<Videogame> findRandom();

}
