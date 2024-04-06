package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.service.PathGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String path;
    @ElementCollection(targetClass = ProductType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "category_product_types", joinColumns = @JoinColumn(name = "category_id"))
    @Enumerated(EnumType.STRING)
    private Set<ProductType> productTypes = new HashSet<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        this.path = new PathGenerator().rusToLatin(name);
    }
}
