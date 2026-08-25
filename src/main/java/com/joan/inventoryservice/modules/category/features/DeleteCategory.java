package com.joan.inventoryservice.modules.category.features;

import com.joan.inventoryservice.modules.category.entity.Category;
import com.joan.inventoryservice.modules.category.helper.CategoryHelper;
import com.joan.inventoryservice.modules.category.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCategory {
    private final CategoryRepository categoryRepository;
    private final CategoryHelper categoryHelper;

    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(value = "categoryById", key="#id"),
                    @CacheEvict(value = "categoryList", allEntries = true)
            }
    )
    public void execute(UUID id) {
        Category category = categoryHelper.findCategoryById(id);

        categoryRepository.delete(category);
    }
}
