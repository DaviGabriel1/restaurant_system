package com.davi.restaurant_burguer.enums;

public enum PaymentMethod {
    CASH(0), DEBIT(1), CREDIT(2), PIX(3);

    private final int id;

    PaymentMethod(int id) {
        this.id = id;
    }

    public static PaymentMethod fromCode(int code) {
        for(PaymentMethod paymentMethod : PaymentMethod.values()) {
            if(paymentMethod.id == code){
                return paymentMethod;
            }
        }
        throw new IllegalArgumentException("método de pagamento inválido");
    }

    public int getId() {
        return id;
    }
}
