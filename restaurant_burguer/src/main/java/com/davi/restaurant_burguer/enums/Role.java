package com.davi.restaurant_burguer.enums;

public enum Role {
    ADMIN(0), USER(1);

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
