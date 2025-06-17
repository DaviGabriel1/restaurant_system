package com.davi.restaurant_burguer.dtos.users;

import com.davi.restaurant_burguer.enums.Provider;
import com.davi.restaurant_burguer.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.OffsetDateTime;

@JsonPropertyOrder({"id","uuid","name","provider","role","createdAt","updatedAt"})
public record ResponseUserDTO(
        long id,
        String uuid,
        String name,
        Role role,
        Provider provider,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("updated_at") OffsetDateTime updatedAt
) {
}
