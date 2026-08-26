package com.joan.inventoryservice.modules.product.dtos.variant.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;

public record AddStockRequest(
        @Positive
        @Max(999999)
        int quantity
) {
}
