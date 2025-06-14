package com.davi.restaurant_burguer.services;

import java.time.OffsetTime;

public class WaitingTimeCalculatorService {

    public static OffsetTime getWaitingTime() {
        return OffsetTime.now(); //TODO: pensar em regra de negócio para retorno de tempo de espera (após o restaurante aceitar o pedido?)
    }
}
