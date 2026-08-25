package com.joan.inventoryservice.modules.product.features.product;

import com.joan.inventoryservice.common.dtos.response.PaginationResponse;
import com.joan.inventoryservice.modules.product.dtos.product.response.ProductResponse;
import com.joan.inventoryservice.modules.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAllProducts {
    private final ProductRepository productRepository;

    public PaginationResponse<ProductResponse> execute(Pageable pageable) {

        Page<ProductResponse> page = productRepository.findAll(pageable)
                .map(
                        ProductResponse::from
                );

        return PaginationResponse.from(page);
    }
}
