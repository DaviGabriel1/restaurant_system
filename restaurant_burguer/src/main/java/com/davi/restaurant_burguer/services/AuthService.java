package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.dtos.auth.RequestRegisterDTO;
import com.davi.restaurant_burguer.dtos.auth.RequestResendCode;
import com.davi.restaurant_burguer.dtos.auth.ResponseLoginDTO;
import com.davi.restaurant_burguer.dtos.auth.ResponseRegisterDTO;
import com.davi.restaurant_burguer.dtos.otp.RequestOtp;
import com.davi.restaurant_burguer.infrastructure.security.TokenService;
import com.davi.restaurant_burguer.mappers.UserMapper;
import com.davi.restaurant_burguer.models.Users;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService{
    private final UserService userService;
    private final TokenService tokenService;
    private final OtpService otpService;
    private final UserMapper userMapper;

    public AuthService(UserService userService, TokenService tokenService, OtpService otpService, UserMapper userMapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.otpService = otpService;
        this.userMapper = userMapper;
    }

    public ResponseRegisterDTO register(RequestRegisterDTO requestRegisterDTO) {
        Users user = this.userService.getUserByPhone(requestRegisterDTO.phone());
        if(user != null){
            return new ResponseRegisterDTO("Este usuário já existe",400);
        }
        Users newUser = this.userMapper.mapToUsers(requestRegisterDTO);
        this.userService.saveUser(newUser);

        this.otpService.generateCode(new RequestOtp(requestRegisterDTO.phone(), requestRegisterDTO.name()));

        return new ResponseRegisterDTO("Confira o código enviado ao seu número",201);
    }

    public ResponseLoginDTO login(Authentication auth) {
        var token = this.tokenService.generateToken((Users) auth.getPrincipal());
        return new ResponseLoginDTO(token);
    }

    public void resendCode(RequestResendCode requestResendCode) {
        this.otpService.generateCode(new RequestOtp(requestResendCode.phone(), requestResendCode.name()));
    }
}
