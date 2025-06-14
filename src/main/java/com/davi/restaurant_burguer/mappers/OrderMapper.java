package com.davi.restaurant_burguer.mappers;

import com.davi.restaurant_burguer.dtos.orders.OrderItemDTO;
import com.davi.restaurant_burguer.dtos.orders.RequestOrdersDTO;
import com.davi.restaurant_burguer.enums.DeliveryType;
import com.davi.restaurant_burguer.enums.PaymentMethod;
import com.davi.restaurant_burguer.models.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class OrderMapper {
    private final OrderItemMapper orderItemMapper;

    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }


    public Order mapToEntity(RequestOrdersDTO dto, Users user, Address address, OffsetTime calculatedWaitingTime, BigDecimal calculatedDeliveryFee) {
        Order orderMapped = new Order();
        orderMapped.setUser(user);
        orderMapped.setAddress(address);
        orderMapped.setWaitingTime(calculatedWaitingTime);
        orderMapped.setChangeFor(new BigDecimal(dto.changeFor()));
        orderMapped.setObservation(dto.observation());

        PaymentMethod paymentMethod = PaymentMethod.fromCode(dto.paymentMethod());
        orderMapped.setPaymentMethod(paymentMethod.getId());

        DeliveryType deliveryType = DeliveryType.fromCode(dto.deliveryType());
        orderMapped.setDeliveryType(deliveryType.getId());

        orderMapped.setDeliveryFee(calculatedDeliveryFee);

        return orderMapped;
    }
}
