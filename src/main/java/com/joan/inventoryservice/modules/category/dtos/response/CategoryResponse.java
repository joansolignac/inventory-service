package com.joan.inventoryservice.modules.category.dtos.response;

import com.joan.inventoryservice.modules.category.entity.Category;

public record CategoryResponse(
        String id,
        String categoryName,
        String description
) {
    public static CategoryResponse from(Category category) {
        return new CategoryResponse(
                category.getId().toString(),
                category.getName(),
                category.getDescription()
        );
    }
}
