package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
public class PurchasedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private int quantity;
    private int border;
    private Double pricePerItem;
    private boolean isOrdered = false;

    public PurchasedProduct() {
    }

    public PurchasedProduct(Product product) {
        itemName = product.getName();
        quantity = 1;
        pricePerItem = product.getPrice();
        border = product.getCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchasedProduct that = (PurchasedProduct) o;
        return Objects.equals(itemName, that.itemName) && Objects.equals(pricePerItem, that.pricePerItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, pricePerItem);
    }
}
