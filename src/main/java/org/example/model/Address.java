package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;
    private String street;
    private String corps;
    private int houseNumber;
    private int flatNumber;

    public Address(String city, String street,String corps, int house_number, int flat_number) {
        this.city = city;
        this.street = street;
        this.corps = corps;
        this.houseNumber = house_number;
        this.flatNumber = flat_number;
    }
    public Address(){}
}
