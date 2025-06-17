package com.davi.restaurant_burguer.dtos.products.additional;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record RequestProductAdditionalDTO(
        Long id,
        String name,
        BigDecimal price,
        @JsonProperty("max_quantity") int maxQuantity,
        String description,
        boolean required) {}
