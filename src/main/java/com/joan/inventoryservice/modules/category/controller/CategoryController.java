package com.joan.inventoryservice.modules.category.controller;

import com.joan.inventoryservice.modules.category.dtos.request.CreateCategoryRequest;
import com.joan.inventoryservice.modules.category.dtos.request.UpdateCategoryRequest;
import com.joan.inventoryservice.modules.category.dtos.response.CategoryResponse;
import com.joan.inventoryservice.modules.category.features.*;
import com.joan.inventoryservice.common.dtos.request.PaginationRequest;
import com.joan.inventoryservice.common.dtos.response.PaginationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CreateCategory createCategory;
    private final FindAllCategories findAllCategories;
    private final GetCategory getCategory;
    private final UpdateCategory updateCategory;
    private final DeleteCategory deleteCategory;

    @PostMapping()
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid CreateCategoryRequest body){
        CategoryResponse response = createCategory.execute(body.categoryName(), body.description());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping()
    public ResponseEntity<PaginationResponse<CategoryResponse>> findAll(@ModelAttribute @Valid PaginationRequest paginationRequest) {
        PaginationResponse<CategoryResponse> response = findAllCategories.execute(paginationRequest.toPageable());

        return ResponseEntity
                .ok(
                        response
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(
                getCategory.execute(UUID.fromString(id))
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable String id,
            @RequestBody @Valid UpdateCategoryRequest body
            ) {
        CategoryResponse response = updateCategory.execute(UUID.fromString(id), body.categoryName(), body.description());

        return ResponseEntity
                .ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleteCategory.execute(UUID.fromString(id));

        return ResponseEntity
                .noContent()
                .build();
    }
}
