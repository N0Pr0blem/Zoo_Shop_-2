package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    ProductType productType;

    public Category() {
    }

    public Category(String name, ProductType productType) {
        this.name = name;
        this.productType = productType;
    }
}
