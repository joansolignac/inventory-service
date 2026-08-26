package com.joan.inventoryservice.modules.product.features.variant;

import com.joan.inventoryservice.modules.product.dtos.variant.response.VariantResponse;
import com.joan.inventoryservice.modules.product.exception.VariantInsufficientException;
import com.joan.inventoryservice.modules.product.helper.VariantHelper;
import com.joan.inventoryservice.modules.product.repository.VariantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReserveStock {
    private final VariantRepository variantRepository;
    private final VariantHelper variantHelper;

    @Transactional
    public void execute(UUID id, int quantity) {

        var variant = variantHelper.findVariantById(id);

        int rowsAffected = variantRepository.reserveStock(variant.getId(), quantity);

        if (rowsAffected <= 0) {
            throw new VariantInsufficientException();
        }
    }
}
