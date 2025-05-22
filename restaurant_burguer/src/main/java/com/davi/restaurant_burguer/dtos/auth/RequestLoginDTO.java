package com.davi.restaurant_burguer.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record RequestLoginDTO(
        @NotBlank(message = "o login é obrigatório") String login,
        @NotBlank(message = "a senha é obrigatória") String password) { //TODO: adicionar um captcha
}
