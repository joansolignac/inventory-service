package com.joan.inventoryservice.modules.product.features.variant;

import com.joan.inventoryservice.modules.product.dtos.variant.response.VariantResponse;
import com.joan.inventoryservice.modules.product.entity.Variant;
import com.joan.inventoryservice.modules.product.helper.VariantHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetVariant {
    private final VariantHelper variantHelper;

    public VariantResponse execute(UUID id) {
        Variant variant = variantHelper.findVariantById(id);

        return VariantResponse.from(variant);
    }
}
