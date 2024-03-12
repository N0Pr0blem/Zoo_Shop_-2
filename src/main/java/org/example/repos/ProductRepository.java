package org.example.repos;

import org.example.model.Product;
import org.example.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
    Product findByType(ProductType type);
}
