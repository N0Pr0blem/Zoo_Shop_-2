package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;
    private Company company;
    private String description;
    private Integer count;
    private String image;

    public Product(String name, Double price, Company company, String description, Integer count, String image) {
        this.name = name;
        this.price = price;
        this.company = company;
        this.description = description;
        this.count = count;
        this.image = image;
    }
}
