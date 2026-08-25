package com.joan.inventoryservice.modules.product.dtos.variant.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

public record UpdateVariantRequest(
        @UUID
        String productId,

        @Size(max = 100)
        String variantName,

        @Size(max = 50)
        String sku,

        @DecimalMin("0.00")
        BigDecimal price,

        @PositiveOrZero
        Integer stock
) {
}
