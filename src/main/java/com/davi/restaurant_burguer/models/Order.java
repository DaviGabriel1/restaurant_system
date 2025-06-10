package com.davi.restaurant_burguer.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    @JoinColumn(name = "address_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @Column(name = "waiting_time", nullable = false)
    private OffsetTime waitingTime;

    @Column(nullable = false)
    private byte status;

    @Column(nullable = false, name = "payment_status")
    private byte paymentStatus;

    @Column
    private OffsetDateTime paymentDate;

    @Column
    private String observation;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @Column(nullable = false, name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "is_confirmed")
    private boolean isConfirmed;

    @Column(nullable = false)
    private byte paymentMethod;

    @Column(nullable = false)
    private byte deliveryType;

    @Column(name = "change_for")
    private BigDecimal changeFor;

    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;
}
