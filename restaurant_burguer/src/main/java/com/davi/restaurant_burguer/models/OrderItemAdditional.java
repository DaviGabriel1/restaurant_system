package com.davi.restaurant_burguer.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item_additional")
public class OrderItemAdditional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "order_item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderItem orderItem;

    @JoinColumn(name = "additional_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductAdditional additional;

    @Column(nullable = false)
    private int quantity;
}
