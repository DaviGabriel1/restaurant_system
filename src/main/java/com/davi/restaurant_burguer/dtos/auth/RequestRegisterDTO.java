package com.davi.restaurant_burguer.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RequestRegisterDTO(
        @NotBlank(message = "o campo nome é obrigatório") String name,
        @NotBlank(message = "o campo telefone é obrigatório")
        @Pattern(regexp = "\\d+", message = "O campo deve conter apenas números")
        String phone
){
}
