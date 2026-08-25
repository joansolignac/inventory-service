package com.joan.inventoryservice.modules.category.features;

import com.joan.inventoryservice.modules.category.entity.Category;
import com.joan.inventoryservice.modules.category.dtos.response.CategoryResponse;
import com.joan.inventoryservice.modules.category.helper.CategoryHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCategory {
    private final CategoryHelper categoryHelper;

    @Cacheable(value = "categoryById", key = "#id")
    public CategoryResponse execute(UUID id) {
        Category category = categoryHelper.findCategoryById(id);

        return CategoryResponse.from(category);
    }
}
