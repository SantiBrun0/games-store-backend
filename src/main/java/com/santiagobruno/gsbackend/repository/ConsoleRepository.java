package com.santiagobruno.gsbackend.repository;

import com.santiagobruno.gsbackend.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {

    Console findByName(String name);

}
