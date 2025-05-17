package com.davi.restaurant_burguer.controllers;

import com.davi.restaurant_burguer.dtos.auth.RequestLoginDTO;
import com.davi.restaurant_burguer.dtos.auth.RequestRegisterDTO;
import com.davi.restaurant_burguer.dtos.auth.ResponseLoginDTO;
import com.davi.restaurant_burguer.dtos.auth.ResponseRegisterDTO;
import com.davi.restaurant_burguer.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    private AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseRegisterDTO> register(@RequestBody RequestRegisterDTO requestRegisterDTO) {
        ResponseRegisterDTO responseRegisterDTO = this.authService.register(requestRegisterDTO);
        if(responseRegisterDTO.status() != 201){
            return ResponseEntity.badRequest().body(responseRegisterDTO);
        }
        return ResponseEntity.status(responseRegisterDTO.status()).body(responseRegisterDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody RequestLoginDTO requestLoginDTO) {
        var emailPassword = new UsernamePasswordAuthenticationToken(requestLoginDTO.login(),requestLoginDTO.password());
        Authentication auth = this.authenticationManager.authenticate(emailPassword);
        ResponseLoginDTO responseLoginDTO = this.authService.login(auth);
        return ResponseEntity.ok(responseLoginDTO);
    }
}
