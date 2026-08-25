package com.joan.inventoryservice.modules.product.dtos.product.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UUID;

public record CreateProductRequest(
        @NotNull
        @UUID
        String categoryId,

        @NotBlank
        @Size(max = 50)
        String productName
) {
}
