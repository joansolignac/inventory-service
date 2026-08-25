package com.joan.inventoryservice.modules.category.helper;

import com.joan.inventoryservice.modules.category.entity.Category;
import com.joan.inventoryservice.modules.category.exception.CategoryNotFoundException;
import com.joan.inventoryservice.modules.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryHelper {
    private final CategoryRepository categoryRepository;

    public Category findCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
