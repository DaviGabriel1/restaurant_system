package com.davi.restaurant_burguer.enums;

public enum DeliveryType {
    DELIVERY(0), PICKUP(1);

    private final int id;

    DeliveryType(int id) {
        this.id = id;
    }

    public static DeliveryType fromCode(int id) {
        for(DeliveryType deliveryType : DeliveryType.values()) {
            if (deliveryType.id == id) {
                return deliveryType;
            }
        }
        throw new IllegalArgumentException("Tipo de entrega inválido");
    }

    public int getId() {
        return id;
    }
}
