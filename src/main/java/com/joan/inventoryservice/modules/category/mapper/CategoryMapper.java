package com.joan.inventoryservice.modules.category.mapper;

import com.joan.inventoryservice.modules.category.dtos.response.CategoryResponse;
import com.joan.inventoryservice.modules.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId().toString(),
                category.getName(),
                category.getDescription()
        );
    }

    public List<CategoryResponse> toResponseList(Page<Category> categories) {
        return categories.map(this::toResponse).toList();
    }
}
