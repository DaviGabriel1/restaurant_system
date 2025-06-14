package com.davi.restaurant_burguer.dtos.orders;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderItemDTO(
        @Min(0) Long productId,
        @Min(0) int quantity,
        @NotBlank String priceUnit, //TODO: retirar campo
        @NotBlank String note,
        @Valid List<RequestAdditionalsDTO> additionals
) {
}
