package com.joan.inventoryservice.modules.product.entity;

import com.joan.inventoryservice.common.model.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "variant")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Variant extends Auditable {

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Setter
    @Column(nullable = false, length = 100)
    private String name;

    @Setter
    @Column(unique = true, nullable = false, length = 50)
    private String sku;

    @Setter
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Setter
    @Column(nullable = false)
    private Integer stock;
}
