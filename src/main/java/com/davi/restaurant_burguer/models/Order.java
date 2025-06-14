package com.davi.restaurant_burguer.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "`order`")
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
    private OffsetTime waitingTime = OffsetTime.now();

    @Column(nullable = false)
    private int status;

    @Column(nullable = false, name = "payment_status")
    private int paymentStatus;

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
    private int paymentMethod;

    @Column(nullable = false)
    private int deliveryType;

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

    public Order() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Order(Users user, Address address, OffsetTime waitingTime,String observation, BigDecimal deliveryFee, BigDecimal totalPrice, int paymentMethod, int deliveryType, BigDecimal changeFor) {
        this.uuid = UUID.randomUUID().toString();
        this.user = user;
        this.address = address;
        this.waitingTime = waitingTime;
        this.observation = observation;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.deliveryType = deliveryType;
        this.changeFor = changeFor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public OffsetTime getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(OffsetTime waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public OffsetDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(OffsetDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }

    public BigDecimal getChangeFor() {
        return changeFor;
    }

    public void setChangeFor(BigDecimal changeFor) {
        this.changeFor = changeFor;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public OffsetDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(OffsetDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", waitingTime=" + waitingTime +
                ", status=" + status +
                ", paymentStatus=" + paymentStatus +
                ", paymentDate=" + paymentDate +
                ", observation='" + observation + '\'' +
                ", deliveryFee=" + deliveryFee +
                ", totalPrice=" + totalPrice +
                ", isConfirmed=" + isConfirmed +
                ", paymentMethod=" + paymentMethod +
                ", deliveryType=" + deliveryType +
                ", changeFor=" + changeFor +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
