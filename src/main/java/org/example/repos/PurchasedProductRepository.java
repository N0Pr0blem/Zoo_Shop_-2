package org.example.repos;

import org.example.model.PurchasedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedProductRepository extends JpaRepository<PurchasedProduct,Long> {
}
