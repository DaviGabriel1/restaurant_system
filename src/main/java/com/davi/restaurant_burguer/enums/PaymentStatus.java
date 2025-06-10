package com.davi.restaurant_burguer.enums;

public enum PaymentStatus {
    PENDING(0), APPROVED(1), REJECTED(2);

    private final int id;

    PaymentStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
