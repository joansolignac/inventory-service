package com.joan.inventoryservice.modules.product.helper;

import org.springframework.stereotype.Component;

@Component
public class SkuGenerator {

    public String generate(String baseSku, String variantName) {
        return String
                .join(
                        "-",
                        baseSku,
                        this.normalize(variantName)
                );
    }

    private String normalize(String value) {
        return value
                .trim()
                .toUpperCase()
                .replaceAll("\\s+", "-");
    }
}
