package com.davi.restaurant_burguer.controllers;

import com.davi.restaurant_burguer.controllers.docs.OrderControllerDocs;
import com.davi.restaurant_burguer.dtos.GenericResponseDTO;
import com.davi.restaurant_burguer.dtos.orders.RequestOrdersDTO;
import com.davi.restaurant_burguer.dtos.sns.MessageDTO;
import com.davi.restaurant_burguer.infrastructure.security.SecurityConfiguration;
import com.davi.restaurant_burguer.interfaces.ISendMessageServiceAdapter;
import com.davi.restaurant_burguer.services.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "controller de CRUD de pedidos")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class OrderController implements OrderControllerDocs {
    private final ISendMessageServiceAdapter snsService;
    private final OrderService orderService;

    public OrderController(ISendMessageServiceAdapter snsService, OrderService orderService) {
        this.snsService = snsService;
        this.orderService = orderService;
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE}
    )
    @Override
    public ResponseEntity<GenericResponseDTO> generateOrder(@RequestBody @Valid RequestOrdersDTO ordersDto) {
        Long orderId = this.orderService.generateOrder(ordersDto);
        this.snsService.publish(new MessageDTO(orderId.toString())); //TODO: enviar a comanda inteira para o SQS através do toString()
        return ResponseEntity.status(201).body(new GenericResponseDTO("mensagem enviada para o SQS"));
    }
}
