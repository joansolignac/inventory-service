package com.joan.inventoryservice.modules.product.entity;

import com.joan.inventoryservice.common.model.Auditable;
import com.joan.inventoryservice.modules.category.entity.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Product extends Auditable {

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Setter
    @Column(unique = true, length = 100)
    private String name;

    @Setter
    @Column(unique = true, length = 100)
    private String baseSku;

    //Relations

    @Builder.Default
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<Variant> variants = new ArrayList<>();
}
