package com.davi.restaurant_burguer.dtos.users;

import com.davi.restaurant_burguer.enums.Provider;
import com.davi.restaurant_burguer.enums.Role;

import java.time.OffsetDateTime;

public record ResponseUserDTO(long id, String uuid, String name, String email, String login, Role role, Provider provider, OffsetDateTime emailVerifiedAt, OffsetDateTime termsAccepted, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
}
