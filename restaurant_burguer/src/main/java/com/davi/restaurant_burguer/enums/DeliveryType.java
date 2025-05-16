package com.davi.restaurant_burguer.enums;

public enum DeliveryType {
    DELIVERY(0), PICKUP(1);

    private final int id;

    DeliveryType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
