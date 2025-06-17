package com.davi.restaurant_burguer.dtos.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderItemDTO(
        @Min(0) @JsonProperty("product_id") Long productId,
        @Min(0) int quantity,
        @NotBlank @JsonProperty("price_unit") String priceUnit, //TODO: retirar campo
        @NotBlank String note,
        @Valid List<RequestAdditionalsDTO> additionals
) {
}
