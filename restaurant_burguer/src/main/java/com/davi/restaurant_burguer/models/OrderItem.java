package com.davi.restaurant_burguer.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "order_item")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, name = "price_unit_at_time")
    private BigDecimal priceUnitAtTime;

    @Column
    private String note;



}
