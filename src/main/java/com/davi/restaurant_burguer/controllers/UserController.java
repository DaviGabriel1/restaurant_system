package com.davi.restaurant_burguer.controllers;

import com.davi.restaurant_burguer.controllers.docs.UserControllerDocs;
import com.davi.restaurant_burguer.dtos.users.ResponseUserDTO;
import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.infrastructure.security.SecurityConfiguration;
import com.davi.restaurant_burguer.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "controller de CRUD de usuários")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class UserController implements UserControllerDocs {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    @Override
    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable long id){
        ResponseUserDTO responseUserDTO = this.userService.getUser(id);
        if(responseUserDTO == null){
            throw new NotfoundException("usuário não encontrado");
        }
        return ResponseEntity.ok(responseUserDTO);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ResponseUserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
