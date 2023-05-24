package com.santiagobruno.gsbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Videogame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate launch;

    @ManyToOne
    @JoinColumn(name = "console_id", nullable = false)
    @JsonIgnoreProperties({"id", "manufacturer", "launch", "price", "stock", "images", "description"})
    private Console console;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "front", column = @Column(name = "image_front", nullable = false)),
            @AttributeOverride(name = "back", column = @Column(name = "image_back", nullable = false)),
            @AttributeOverride(name = "extra", column = @Column(name = "image_extra", nullable = false))
    })
    private Images images;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Videogame videogame)) return false;
        return name.equals(videogame.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
