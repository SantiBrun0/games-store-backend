package com.santiagobruno.gsbackend.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Images {
    private String front;
    private String back;
    private String extra;
}
