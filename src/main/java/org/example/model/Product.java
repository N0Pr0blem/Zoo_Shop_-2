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
    private Double discount;
    @Enumerated(EnumType.STRING)
    private ProductType type;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String description;
    private Integer count;
    private String image;

    public Product(String name, Double price,Double discount, Company company, String description, Integer count,ProductType type,Category category) {
        this.name = name;
        this.price = price;
        this.company = company;
        this.description = description;
        this.count = count;
        this.discount = discount;
        this.type = type;
        this.category = category;
    }
    public Product(){}
}
