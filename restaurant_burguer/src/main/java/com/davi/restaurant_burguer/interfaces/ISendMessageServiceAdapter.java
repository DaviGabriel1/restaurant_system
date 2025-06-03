package com.davi.restaurant_burguer.interfaces;

import com.davi.restaurant_burguer.dtos.sns.MessageDTO;

public interface ISendMessageServiceAdapter {
    void sendMessage(String message,String destination);
    void publish(MessageDTO message);
}
