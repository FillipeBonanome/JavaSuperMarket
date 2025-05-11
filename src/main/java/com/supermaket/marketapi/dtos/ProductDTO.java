package com.supermaket.marketapi.dtos;

import com.supermaket.marketapi.UnitType;
import com.supermaket.marketapi.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        @NotBlank(message = "Product name must not be null")
        String name,
        @NotNull(message = "Product code must not be null")
        Long code,
        @NotNull(message = "Product unit price must not be null")
        Float unitPrice,
        @NotNull(message = "Product unit type must not be null")
        UnitType unitType,
        @NotNull(message = "Product tax percent must not be null")
        Float taxPercent
) {
        public ProductDTO(Product product) {
                this(product.getName(), product.getCode(), product.getUnitPrice(), product.getUnitType(), product.getTaxPercent());
        }
}
