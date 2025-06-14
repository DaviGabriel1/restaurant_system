package com.davi.restaurant_burguer.controllers;

import com.davi.restaurant_burguer.dtos.GenericResponseDTO;
import com.davi.restaurant_burguer.dtos.orders.RequestOrdersDTO;
import com.davi.restaurant_burguer.dtos.sns.MessageDTO;
import com.davi.restaurant_burguer.interfaces.ISendMessageServiceAdapter;
import com.davi.restaurant_burguer.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private ISendMessageServiceAdapter snsService;
    private OrderService orderService;

    public OrderController(ISendMessageServiceAdapter snsService, OrderService orderService) {
        this.snsService = snsService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<GenericResponseDTO> generateOrder(@RequestBody @Valid RequestOrdersDTO ordersDto) {
        Long orderId = this.orderService.generateOrder(ordersDto);
        this.snsService.publish(new MessageDTO(orderId.toString())); //TODO: enviar a comanda inteira para o SQS
        return ResponseEntity.status(201).body(new GenericResponseDTO("mensagem enviada para o SQS"));
    }
}
