package com.joan.inventoryservice.modules.category.entity;

import com.joan.inventoryservice.common.model.Auditable;
import com.joan.inventoryservice.modules.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Category extends Auditable {
    @Setter
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Setter
    @Column(length = 500)
    private String description;

    //Relations

    @Builder.Default
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();
}
