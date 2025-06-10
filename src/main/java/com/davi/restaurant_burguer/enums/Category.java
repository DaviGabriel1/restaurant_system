package com.davi.restaurant_burguer.enums;

public enum Category {
    BURGER(0), DRINK(1), ACCOMPANIMENT(2), SAUCE(3), OTHER(4);

    private final int id;

    Category(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
