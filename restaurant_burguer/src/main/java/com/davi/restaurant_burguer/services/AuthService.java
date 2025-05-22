package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.dtos.auth.RequestRegisterDTO;
import com.davi.restaurant_burguer.dtos.auth.ResponseLoginDTO;
import com.davi.restaurant_burguer.dtos.auth.ResponseRegisterDTO;
import com.davi.restaurant_burguer.infrastructure.security.TokenService;
import com.davi.restaurant_burguer.mappers.UserMapper;
import com.davi.restaurant_burguer.models.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final UserService userService;
    private final TokenService tokenService;
    private final UserMapper userMapper;

    public AuthService(UserService userService,TokenService tokenService, UserMapper userMapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
    }

    public ResponseRegisterDTO register(RequestRegisterDTO requestRegisterDTO) {
        Users user = this.userService.getUserByLogin(requestRegisterDTO.login() == null ? requestRegisterDTO.email() : requestRegisterDTO.login());
        if(user != null){
            return new ResponseRegisterDTO("Este usuário já existe",400);
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(requestRegisterDTO.password());
        RequestRegisterDTO updatedRequest = new RequestRegisterDTO(
            requestRegisterDTO.name(),
            requestRegisterDTO.email(),
            requestRegisterDTO.login(),
            encryptedPassword,
            requestRegisterDTO.termsAccepted()
        );
        Users newUser = this.userMapper.mapToUsers(updatedRequest);

        this.userService.saveUser(newUser);

        return new ResponseRegisterDTO("Usuário cadastrado com sucesso",201);
    }

    public ResponseLoginDTO login(Authentication auth) {
        var token = this.tokenService.generateToken((Users) auth.getPrincipal());
        return new ResponseLoginDTO(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userService.getUserByLogin(username) == null ? null : this.userService.getUserByLogin(username);
    }
}
