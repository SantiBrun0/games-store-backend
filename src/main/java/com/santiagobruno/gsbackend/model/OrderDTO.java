package com.santiagobruno.gsbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record OrderDTO(
        List<Integer> videogames,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate date,
        String fullNameCustomer,
        String dni,
        String phoneNumber,
        Address address,
        Double mount
) {
}
