package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.mappers.AdditionalMapper;
import com.davi.restaurant_burguer.mappers.OrderItemMapper;
import com.davi.restaurant_burguer.models.OrderItem;
import com.davi.restaurant_burguer.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemMapper = new OrderItemMapper(new AdditionalMapper());
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> saveAllOrderItem(List<OrderItem> orderItens) {
        return this.orderItemRepository.saveAll(orderItens);
    }
}
