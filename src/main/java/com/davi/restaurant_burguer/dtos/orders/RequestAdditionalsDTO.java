package com.davi.restaurant_burguer.dtos.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record RequestAdditionalsDTO(
        @Min(0) @JsonProperty("additional_id") Long additionalId,
        @Min(0) int quantity
) {
}
