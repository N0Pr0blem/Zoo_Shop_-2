package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Buy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private LocalDateTime purchaseDateTime;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PurchasedProduct> purchasedProduct;
    private BigDecimal totalCost;
}
