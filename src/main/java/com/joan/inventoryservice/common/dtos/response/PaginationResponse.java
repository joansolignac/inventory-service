package com.joan.inventoryservice.common.dtos.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record PaginationResponse<T>(
        List<T> data,
        int page,
        int size,
        long total
) {
    public static <T> PaginationResponse<T> from(Page<T> page) {
        return new PaginationResponse<T>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }
}
