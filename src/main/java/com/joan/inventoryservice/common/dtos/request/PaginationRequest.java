package com.joan.inventoryservice.common.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public record PaginationRequest(
        @Min(0)
        Integer page,

        @Min(1)
        @Max(100)
        Integer size
) {
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 10;


    public PaginationRequest {
        if (page == null) page = DEFAULT_PAGE;
        if (size == null) size = DEFAULT_SIZE;
    }

    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }
}
