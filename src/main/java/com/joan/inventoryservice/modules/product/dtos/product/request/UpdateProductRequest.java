package com.joan.inventoryservice.modules.product.dtos.product.request;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UUID;

public record UpdateProductRequest(
        @UUID
        String categoryId,

        @Size(max = 50)
        String productName
) {
}
