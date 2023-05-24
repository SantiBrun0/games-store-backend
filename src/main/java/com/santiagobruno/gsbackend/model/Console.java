package com.santiagobruno.gsbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Console {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate launch;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "front", column = @Column(name = "image_front")),
            @AttributeOverride(name = "back", column = @Column(name = "image_back")),
            @AttributeOverride(name = "extra", column = @Column(name = "image_extra"))
    })
    private Images images;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Console console)) return false;
        return name.equals(console.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
