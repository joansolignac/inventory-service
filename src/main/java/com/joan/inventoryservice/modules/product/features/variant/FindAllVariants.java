package com.joan.inventoryservice.modules.product.features.variant;

import com.joan.inventoryservice.common.dtos.response.PaginationResponse;
import com.joan.inventoryservice.modules.product.dtos.variant.response.VariantResponse;
import com.joan.inventoryservice.modules.product.repository.VariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAllVariants {
    private final VariantRepository variantRepository;

    public PaginationResponse<VariantResponse> execute(Pageable pageable) {
        Page<VariantResponse> page = variantRepository.findAll(pageable)
                .map(VariantResponse::from);

        return PaginationResponse.from(page);
    }
}
