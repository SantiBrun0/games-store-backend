package com.santiagobruno.gsbackend.model;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {
    private String street;
    private String city;
    private String province;
    private String country;
}
