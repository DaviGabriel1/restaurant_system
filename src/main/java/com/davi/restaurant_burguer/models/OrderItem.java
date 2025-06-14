package com.davi.restaurant_burguer.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, name = "price_unit_at_time")
    private BigDecimal priceUnitAtTime;

    @Column
    private String note;

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemAdditional> orderItemAdditionals = new ArrayList<>();

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, int quantity, BigDecimal priceUnitAtTime, String note) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.priceUnitAtTime = priceUnitAtTime;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceUnitAtTime() {
        return priceUnitAtTime;
    }

    public void setPriceUnitAtTime(BigDecimal priceUnitAtTime) {
        this.priceUnitAtTime = priceUnitAtTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<OrderItemAdditional> getOrderItemAdditionals() {
        return orderItemAdditionals;
    }

    public void setOrderItemAdditionals(List<OrderItemAdditional> orderItemAdditionals) {
        this.orderItemAdditionals = orderItemAdditionals;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", priceUnitAtTime=" + priceUnitAtTime +
                ", note='" + note + '\'' +
                '}';
    }
}
