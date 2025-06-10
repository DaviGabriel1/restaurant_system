package com.davi.restaurant_burguer.enums;

public enum DeliveryStatus {
    PENDING(0), IN_PREPARE(1), IN_PROGRESS(2), COMPLETED(3), DELIVERED(4), CANCELLED(5);

    private final int id;

    DeliveryStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
