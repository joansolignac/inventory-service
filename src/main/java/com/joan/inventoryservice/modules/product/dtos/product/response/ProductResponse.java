package com.joan.inventoryservice.modules.product.dtos.product.response;

import com.joan.inventoryservice.modules.product.entity.Product;

public record ProductResponse(
        String productId,
        String categoryId,
        String categoryName,
        String productName,
        String baseSku
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId().toString(),
                product.getCategory().getId().toString(),
                product.getCategory().getName(),
                product.getName(),
                product.getBaseSku()
        );
    }
}
