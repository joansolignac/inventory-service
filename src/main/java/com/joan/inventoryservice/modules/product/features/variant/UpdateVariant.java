package com.joan.inventoryservice.modules.product.features.variant;

import com.joan.inventoryservice.modules.product.commands.UpdateVariantCommand;
import com.joan.inventoryservice.modules.product.dtos.variant.response.VariantResponse;
import com.joan.inventoryservice.modules.product.entity.Product;
import com.joan.inventoryservice.modules.product.entity.Variant;
import com.joan.inventoryservice.modules.product.exception.VariantAlreadyExistsException;
import com.joan.inventoryservice.modules.product.helper.ProductHelper;
import com.joan.inventoryservice.modules.product.helper.SkuGenerator;
import com.joan.inventoryservice.modules.product.helper.VariantHelper;
import com.joan.inventoryservice.modules.product.repository.VariantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateVariant {
    private final VariantRepository variantRepository;
    private final SkuGenerator skuGenerator;
    private final ProductHelper productHelper;
    private final VariantHelper variantHelper;

    @Transactional
    public VariantResponse execute(UpdateVariantCommand command) {
        Variant variant = variantHelper.findVariantById(command.id());

        if (command.productId() != null) {
            Product product = productHelper.findProductById(command.productId());

            variant.setProduct(product);

            variant.setSku(
                    skuGenerator.generate(
                            product.getBaseSku(),
                            variant.getName()
                    )
            );
        }

        if (command.variantName() != null) {
            String normalizedVariantName = command.variantName().trim().toUpperCase();

            variant.setName(normalizedVariantName);

            variant.setSku(
                    skuGenerator.generate(
                            variant.getProduct().getBaseSku(),
                            normalizedVariantName
                    )
            );
        }

        if (command.price() != null) {
            variant.setPrice(command.price());
        }

        if (command.stock() != null) {
            variant.setStock(command.stock());
        }

        return VariantResponse.from(variantRepository.save(variant));
    }
}
