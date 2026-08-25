package com.joan.inventoryservice.modules.category.features;

import com.joan.inventoryservice.modules.category.dtos.response.CategoryResponse;
import com.joan.inventoryservice.modules.category.entity.Category;
import com.joan.inventoryservice.modules.category.exception.CategoryAlreadyExistsException;
import com.joan.inventoryservice.modules.category.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategory {
    private final CategoryRepository categoryRepository;

    @Transactional
    @CacheEvict(value = "categoryList", allEntries = true)
    public CategoryResponse execute(String categoryName, String description) {
        String normalizedName = categoryName.trim().toUpperCase();

        //Description can be null
        String normalizedDescription = description != null ? description.trim().toUpperCase() : null;

        if (categoryRepository.existsByName(normalizedName)) {
            throw new CategoryAlreadyExistsException(normalizedName);
        }

        Category category = Category
                .builder()
                .name(normalizedName)
                .description(normalizedDescription)
                .build();

        return CategoryResponse.from(categoryRepository.save(category));
    }
}
