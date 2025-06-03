package com.davi.restaurant_burguer.controllers;

import com.davi.restaurant_burguer.dtos.GenericResponseDTO;
import com.davi.restaurant_burguer.dtos.sns.MessageDTO;
import com.davi.restaurant_burguer.interfaces.ISendMessageServiceAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private ISendMessageServiceAdapter snsService;

    public OrderController(ISendMessageServiceAdapter snsService) {
        this.snsService = snsService;
    }

    @PostMapping
    public ResponseEntity<GenericResponseDTO> generateOrder() {
        String message = "mensagem mock de pedido enviado ao SQS";
        this.snsService.publish(new MessageDTO(message));
        return ResponseEntity.status(201).body(new GenericResponseDTO("mensagem enviada para o SQS"));
    }
}
