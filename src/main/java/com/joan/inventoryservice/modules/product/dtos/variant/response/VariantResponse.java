package com.joan.inventoryservice.modules.product.dtos.variant.response;

import com.joan.inventoryservice.modules.product.entity.Variant;

import java.math.BigDecimal;

public record VariantResponse(
        String variantId,
        String productId,
        String productName,
        String variantName,
        String sku,
        BigDecimal price,
        Integer stock
) {
    public static VariantResponse from(Variant variant) {
        return new VariantResponse(
                variant.getId().toString(),
                variant.getProduct().getId().toString(),
                variant.getProduct().getName(),
                variant.getName(),
                variant.getSku(),
                variant.getPrice(),
                variant.getStock()
        );
    }
}
