package com.joan.inventoryservice.modules.category.features;

import com.joan.inventoryservice.modules.category.dtos.response.CategoryResponse;
import com.joan.inventoryservice.modules.category.entity.Category;
import com.joan.inventoryservice.modules.category.exception.CategoryAlreadyExistsException;
import com.joan.inventoryservice.modules.category.helper.CategoryHelper;
import com.joan.inventoryservice.modules.category.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateCategory {
    private final CategoryRepository categoryRepository;
    private final CategoryHelper categoryHelper;

    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(value = "categoryById", key="#id"),
                    @CacheEvict(value = "categoryList", allEntries = true)
            }
    )
    public CategoryResponse execute(UUID id, String categoryName, String description) {
        Category category = categoryHelper.findCategoryById(id);

        if (categoryName !=  null) {
            String normalizedName = categoryName.toUpperCase();

            if (categoryRepository.existsByNameAndIdNot(
                    normalizedName,
                    id
            )) {
                throw new CategoryAlreadyExistsException(category.getName());
            }

            category.setName(
                    normalizedName
            );
        }

        if (description != null) {
            category.setDescription(
                    description.toUpperCase()
            );
        }

        return CategoryResponse.from(categoryRepository.save(category));
    }
}
