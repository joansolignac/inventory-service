package com.joan.inventoryservice.modules.product.features.variant;

import com.joan.inventoryservice.common.dtos.response.PaginationResponse;
import com.joan.inventoryservice.modules.product.dtos.variant.response.VariantResponse;
import com.joan.inventoryservice.modules.product.helper.ProductHelper;
import com.joan.inventoryservice.modules.product.repository.VariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindAllVariantsByProduct {
    private final VariantRepository variantRepository;
    private final ProductHelper productHelper;

    public PaginationResponse<VariantResponse> execute(UUID productId, Pageable pageable) {
        productHelper.findProductById(productId);

        Page<VariantResponse> page = variantRepository.findAllByProductId(productId, pageable)
                .map(VariantResponse::from);

        return PaginationResponse.from(page);
    }
}
