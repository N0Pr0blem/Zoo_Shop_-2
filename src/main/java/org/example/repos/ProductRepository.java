package org.example.repos;

import org.example.model.Product;
import org.example.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    List<Product> findByType(ProductType type);
}
