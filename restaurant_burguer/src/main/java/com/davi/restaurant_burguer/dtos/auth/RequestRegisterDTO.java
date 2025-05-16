package com.davi.restaurant_burguer.dtos.auth;

public record RequestRegisterDTO(String name, String email, String login, String password, boolean termsAccepted){
}
