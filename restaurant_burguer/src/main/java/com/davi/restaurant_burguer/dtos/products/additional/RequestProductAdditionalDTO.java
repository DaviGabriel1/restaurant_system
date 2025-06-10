package com.davi.restaurant_burguer.dtos.products.additional;

import java.math.BigDecimal;

public record RequestProductAdditionalDTO(Long id, String name, BigDecimal price, int maxQuantity, String description, boolean required) {}
