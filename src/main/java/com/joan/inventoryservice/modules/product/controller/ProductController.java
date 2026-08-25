package com.joan.inventoryservice.modules.product.controller;

import com.joan.inventoryservice.common.dtos.request.PaginationRequest;
import com.joan.inventoryservice.common.dtos.response.PaginationResponse;
import com.joan.inventoryservice.modules.product.dtos.product.request.CreateProductRequest;
import com.joan.inventoryservice.modules.product.dtos.product.request.UpdateProductRequest;
import com.joan.inventoryservice.modules.product.dtos.product.response.ProductResponse;
import com.joan.inventoryservice.modules.product.dtos.variant.response.VariantResponse;
import com.joan.inventoryservice.modules.product.features.product.CreateProduct;
import com.joan.inventoryservice.modules.product.features.product.DeleteProduct;
import com.joan.inventoryservice.modules.product.features.product.FindAllProducts;
import com.joan.inventoryservice.modules.product.features.product.GetProduct;
import com.joan.inventoryservice.modules.product.features.product.UpdateProduct;
import com.joan.inventoryservice.modules.product.features.variant.FindAllVariantsByProduct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProduct createProduct;
    private final FindAllProducts findAllProducts;
    private final GetProduct getProduct;
    private final UpdateProduct updateProduct;
    private final DeleteProduct deleteProduct;
    private final FindAllVariantsByProduct findAllVariantsByProduct;

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid CreateProductRequest body) {
        var product = createProduct.execute(
                UUID.fromString(body.categoryId()),
                body.productName()

        );

        return ResponseEntity.ok(
                product
        );
    };

    @GetMapping()
    public ResponseEntity<PaginationResponse<ProductResponse>> findAll(@ModelAttribute @Valid PaginationRequest request) {
        var response = findAllProducts.execute(request.toPageable());

        return ResponseEntity
                .ok(
                        response
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(
                getProduct.execute(UUID.fromString(id))
        );
    }

    @GetMapping("/{id}/variants")
    public ResponseEntity<PaginationResponse<VariantResponse>> findAllVariants(
            @PathVariable String id,
            @ModelAttribute @Valid PaginationRequest request
    ) {
        var response = findAllVariantsByProduct.execute(UUID.fromString(id), request.toPageable());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable String id,
            @RequestBody @Valid UpdateProductRequest body
    ) {
        var product = updateProduct.execute(
                UUID.fromString(id),
                body.categoryId() != null ? UUID.fromString(body.categoryId()) : null,
                body.productName()
        );

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleteProduct.execute(UUID.fromString(id));

        return ResponseEntity
                .noContent()
                .build();
    }
}
