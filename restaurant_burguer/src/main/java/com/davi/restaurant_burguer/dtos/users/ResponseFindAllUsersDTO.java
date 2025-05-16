package com.davi.restaurant_burguer.dtos.users;

import com.davi.restaurant_burguer.enums.Role;

import java.time.OffsetDateTime;

public record ResponseFindAllUsersDTO(long id, String uuid, String name, String email, String login, String role, String provider,OffsetDateTime emailVerifiedAt, OffsetDateTime termsAccepted, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
}
