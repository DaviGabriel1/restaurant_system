package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.dtos.orders.OrderItemDTO;
import com.davi.restaurant_burguer.dtos.orders.RequestAdditionalsDTO;
import com.davi.restaurant_burguer.dtos.orders.RequestOrdersDTO;
import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.mappers.AdditionalMapper;
import com.davi.restaurant_burguer.mappers.OrderItemMapper;
import com.davi.restaurant_burguer.mappers.OrderMapper;
import com.davi.restaurant_burguer.models.*;
import com.davi.restaurant_burguer.repositories.OrderRepository;
import com.davi.restaurant_burguer.utils.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final UserService userService;
    private final AddressService addressService;
    private final OrderItemService orderItemService;
    private final OrderItemAdditionalService orderItemAdditionalService;
    private final ProductService productService;
    private final DeliveryFeeServiceMock deliveryFeeService;
    private final AdditionalService additionalService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final AdditionalMapper additionalMapper;

    public OrderService(UserService userService, AddressService addressService, OrderItemService orderItemService, OrderItemAdditionalService orderItemAdditionalService, ProductService productService, DeliveryFeeServiceMock deliveryFeeService, AdditionalService additionalService, OrderRepository orderRepository, OrderMapper orderMapper, OrderItemMapper orderItemMapper, AdditionalMapper additionalMapper) {
        this.userService = userService;
        this.addressService = addressService;
        this.orderItemService = orderItemService;
        this.orderItemAdditionalService = orderItemAdditionalService;
        this.productService = productService;
        this.deliveryFeeService = deliveryFeeService;
        this.additionalService = additionalService;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.additionalMapper = additionalMapper;
    }

    @Transactional
    public Long generateOrder(RequestOrdersDTO ordersDTO) {
        Users user = this.userService.getUserById(ordersDTO.userId());
        Address address = this.addressService.getAddressById(user,ordersDTO.addressId());

        List<Long> ids = ordersDTO.itens().stream().map(OrderItemDTO::productId).toList(); // busca o id de todos os orderItemDTO da lista com streams

        List<Product> products = this.productService.getByIdIn(ids);

        if(!ListUtils.validateIfAllProductsWereFound(ids,products)) {
            throw new NotfoundException("produtos não encontrados");
        }

        Order orderMapped = this.orderMapper.mapToEntity(ordersDTO,
                user,
                address,
                WaitingTimeCalculatorService.getWaitingTime(),
                this.deliveryFeeService.calculateDeliveryFee(address)
        );

        orderMapped.setTotalPrice(new BigDecimal("100.00")); //TODO: adicionar o totalPrice no mapeamento

        Order orderCreated = this.orderRepository.save(orderMapped);

        List<Product> allProducts = this.productService.getAllProducts();
        List<ProductAdditional> allProductsAdditionals = this.additionalService.findAll();

        List<OrderItem> ordersItens = this.orderItemMapper.mapListToEntity(ordersDTO.itens(),orderCreated,allProducts,allProductsAdditionals);
        this.orderItemService.saveAllOrderItem(ordersItens);
        return orderCreated.getId();
    }

}
