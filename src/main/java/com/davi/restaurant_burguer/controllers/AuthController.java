package com.davi.restaurant_burguer.controllers;

import com.davi.restaurant_burguer.controllers.docs.AuthControllerDocs;
import com.davi.restaurant_burguer.dtos.GenericResponseDTO;
import com.davi.restaurant_burguer.dtos.auth.*;
import com.davi.restaurant_burguer.dtos.products.ResponseProductDTO;
import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.infrastructure.security.PhoneCodeAuthenticationToken;
import com.davi.restaurant_burguer.infrastructure.security.SecurityConfiguration;
import com.davi.restaurant_burguer.services.AuthService;
import com.davi.restaurant_burguer.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "controller de autenticação/autorização")
public class AuthController implements AuthControllerDocs {
    private final AuthService authService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, UserService userService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value ="/register",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE}
    )
    @Override
    public ResponseEntity<ResponseRegisterDTO> register(@RequestBody @Valid RequestRegisterDTO requestRegisterDTO) {
        ResponseRegisterDTO responseRegisterDTO = this.authService.register(requestRegisterDTO);
        if(responseRegisterDTO.status() != 201){
            return ResponseEntity.badRequest().body(responseRegisterDTO);
        }
        return ResponseEntity.status(responseRegisterDTO.status()).body(responseRegisterDTO);
    }

    @PostMapping(value = "/login",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE}
    )
    @Override
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody @Valid RequestLoginDTO requestLoginDTO) {
        if(this.userService.getUserByPhone(requestLoginDTO.phone()) == null){
            throw new NotfoundException("usuário não cadastrado");
        }
        var authToken = new PhoneCodeAuthenticationToken(requestLoginDTO.phone(), requestLoginDTO.code());
        Authentication auth = this.authenticationManager.authenticate(authToken);
        ResponseLoginDTO responseLoginDTO = this.authService.login(auth);
        return ResponseEntity.ok(responseLoginDTO);
    }

    @PostMapping(value = "/resend-code",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE}
    )
    @Override
    public ResponseEntity<GenericResponseDTO> resendCode(@RequestBody @Valid RequestResendCode requestResendCode) {
        this.authService.resendCode(requestResendCode);
        return ResponseEntity.ok(new GenericResponseDTO("código reenviado para o número"));
    }
}
