package com.joan.inventoryservice.modules.category.dtos.request;

import jakarta.validation.constraints.Size;

public record UpdateCategoryRequest(
        @Size(max = 100)
        String categoryName,

        @Size(max = 500)
        String description
) {
}
