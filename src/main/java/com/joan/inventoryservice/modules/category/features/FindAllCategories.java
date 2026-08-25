package com.joan.inventoryservice.modules.category.features;

import com.joan.inventoryservice.common.dtos.response.PaginationResponse;
import com.joan.inventoryservice.modules.category.dtos.response.CategoryResponse;
import com.joan.inventoryservice.modules.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAllCategories {
    private final CategoryRepository categoryRepository;

    @Cacheable(
            value = "categoryList",
            key = "'page=' + #pageable.pageNumber + ':size=' + #pageable.pageSize + ':sort=' + #pageable.sort.toString()"
    )
    public PaginationResponse<CategoryResponse> execute(Pageable pageable) {
        Page<CategoryResponse> page = categoryRepository.findAll(pageable)
                .map(CategoryResponse::from);

        return PaginationResponse.from(page);
    }
}
