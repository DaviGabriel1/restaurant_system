package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.models.Address;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DeliveryFeeServiceMock {

    public BigDecimal calculateDeliveryFee(Address address) {
        return new BigDecimal("9.99"); //TODO: gerar lógica de calculo de taxa de entrega (deve ser customizavel pelo restaurante) (Gerar pelo Cep ou distancia vetorial?)
    }
}
