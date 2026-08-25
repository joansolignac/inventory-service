package com.joan.inventoryservice.modules.product.dtos.variant.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

public record CreateVariantRequest(
        @NotNull
        @UUID
        String productId,

        @NotBlank
        @Size(max = 100)
        String variantName,

        @NotNull
        @DecimalMin("0.00")
        BigDecimal price,

        @NotNull
        @PositiveOrZero
        Integer stock
) {
}
