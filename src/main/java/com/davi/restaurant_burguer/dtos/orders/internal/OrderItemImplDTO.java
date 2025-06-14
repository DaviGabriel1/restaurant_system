package com.davi.restaurant_burguer.dtos.orders.internal;

import com.davi.restaurant_burguer.models.Order;
import com.davi.restaurant_burguer.models.Product;

import java.math.BigDecimal;

public record OrderItemImplDTO(Order order, Product product, int quantity, BigDecimal priceUnitAtTime, String note) {
}
