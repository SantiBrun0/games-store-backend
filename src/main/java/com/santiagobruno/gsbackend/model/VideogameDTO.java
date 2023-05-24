package com.santiagobruno.gsbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record VideogameDTO(
        String name,
        String genre,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate launch,
        Integer stock,
        Double price,
        String description,
        String console,
        Images images
) {
}
