package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private LocalDateTime purchaseDateTime;
    @ManyToMany
    @JoinTable(
            name = "cheque_product",
            joinColumns = @JoinColumn(name = "cheque_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<PurchasedProduct> purchasedProduct;
    private Double totalCost;

    public Cheque() {
    }

    public Cheque(Long userId, List<PurchasedProduct> purchasedProduct, Double totalCost) {
        this.userId = userId;
        this.purchaseDateTime =LocalDateTime.now();
        this.purchasedProduct = new ArrayList<>();
        for (PurchasedProduct product : purchasedProduct){
            product.setOrdered(true);
            this.purchasedProduct.add(product);
        }
        this.totalCost = totalCost;
    }
}
