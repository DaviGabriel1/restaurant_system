package com.davi.restaurant_burguer.services.aws;

import com.davi.restaurant_burguer.dtos.sns.ISNSMessage;
import com.davi.restaurant_burguer.dtos.sns.MessageDTO;
import com.davi.restaurant_burguer.dtos.sns.OrdersEmitDTO;
import com.davi.restaurant_burguer.interfaces.ISendMessageServiceAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.Topic;

import java.util.UUID;

@Service
public class SnsService implements ISendMessageServiceAdapter {
    private final SnsClient amazonSNSClient;
    private final Topic sendMessageTopic;
    private final OrdersEmitDTO ordersEmitTopicArn;

    public SnsService(SnsClient amazonSNSClient,@Qualifier("sendMessageTopic") Topic sendMessageTopic,@Qualifier("ordersEmitTopic") OrdersEmitDTO ordersEmitTopicArn){
        this.amazonSNSClient = amazonSNSClient;
        this.sendMessageTopic = sendMessageTopic;
        this.ordersEmitTopicArn = ordersEmitTopicArn;
    }

    @Override
    public void sendMessage(String message, String destination) { //TODO: adicionar o destination nas mensagens OTP
        PublishRequest publishRequest = PublishRequest
                .builder().message(message)
                .topicArn(sendMessageTopic.topicArn()).subject("código de confirmação").build();
        PublishResponse response = amazonSNSClient.publish(publishRequest);
        System.out.println("mensagem enviada com ID: " + response.messageId());
    }

    @Override
    public void publish(MessageDTO message) {
        System.out.println(sendMessageTopic);
        System.out.println(ordersEmitTopicArn);

        String deduplicationId = UUID.randomUUID().toString();

        PublishRequest publishRequest = PublishRequest.builder()
                .message(message.message())
                .messageGroupId(ordersEmitTopicArn.groupId())
                .messageDeduplicationId(deduplicationId)
                .topicArn(ordersEmitTopicArn.arn())
                .build();
        this.amazonSNSClient.publish(publishRequest);
    }
}
