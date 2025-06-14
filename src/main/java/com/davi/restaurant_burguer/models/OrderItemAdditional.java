package com.davi.restaurant_burguer.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item_additional")
public class OrderItemAdditional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "order_item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderItem orderItem;

    @JoinColumn(name = "additional_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductAdditional additional;

    @Column(nullable = false)
    private int quantity;

    public OrderItemAdditional(OrderItem orderItem, ProductAdditional additional, int quantity) {
        this.orderItem = orderItem;
        this.additional = additional;
        this.quantity = quantity;
    }

    public OrderItemAdditional() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public ProductAdditional getAdditional() {
        return additional;
    }

    public void setAdditional(ProductAdditional additional) {
        this.additional = additional;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemAdditional{" +
                "id=" + id +
                ", orderItem=" + orderItem +
                ", additional=" + additional +
                ", quantity=" + quantity +
                '}';
    }
}
