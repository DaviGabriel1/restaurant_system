package com.davi.restaurant_burguer.enums;

public enum Provider {
    EMAIL(0), GOOGLE(1);

    private final int id;

    Provider(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
