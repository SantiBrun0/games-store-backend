package com.santiagobruno.gsbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JsonIgnoreProperties({"id", "genre", "launch", "price", "stock", "images", "description"})
    private List<Videogame> videogames;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Column(nullable = false)
    private String fullNameCustomer;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false)
    private String phoneNumber;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "customer_street", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "customer_city", nullable = false)),
            @AttributeOverride(name = "province", column = @Column(name = "customer_province", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "customer_country", nullable = false))
    })
    private Address address;

    @Column(nullable = false, unique = true)
    private String trackingCode;

    @Column(nullable = false)
    private Double mount;

}
