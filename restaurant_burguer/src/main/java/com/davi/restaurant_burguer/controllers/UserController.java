package com.davi.restaurant_burguer.controllers;

import com.davi.restaurant_burguer.dtos.users.ResponseUserDTO;
import com.davi.restaurant_burguer.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable long id){
        ResponseUserDTO responseUserDTO = this.userService.getUser(id);
        return ResponseEntity.ok(responseUserDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
