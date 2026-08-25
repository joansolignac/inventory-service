package com.joan.inventoryservice.modules.product.features.variant;

import com.joan.inventoryservice.common.dtos.response.PaginationResponse;
import com.joan.inventoryservice.modules.product.dtos.variant.response.VariantResponse;
import com.joan.inventoryservice.modules.product.entity.Product;
import com.joan.inventoryservice.modules.product.exception.ProductNotFoundException;
import com.joan.inventoryservice.modules.product.repository.ProductRepository;
import com.joan.inventoryservice.modules.product.repository.VariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindAllVariantsByProduct {
    private final ProductRepository productRepository;
    private final VariantRepository variantRepository;

    public PaginationResponse<VariantResponse> execute(UUID productId, Pageable pageable) {
        findProduct(productId);

        Page<VariantResponse> page = variantRepository.findAllByProductId(productId, pageable)
                .map(VariantResponse::from);

        return PaginationResponse.from(page);
    }

    private Product findProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
