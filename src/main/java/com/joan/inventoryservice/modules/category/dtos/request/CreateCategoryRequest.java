package com.joan.inventoryservice.modules.category.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(
        @NotBlank()
        @Size(max = 100)
        String categoryName,

        @Size(max = 500)
        String description
) { }
