package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.interfaces.ISendMessageServiceAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Service
public class SnsService implements ISendMessageServiceAdapter {
    private final SnsClient amazonSNSClient;

    @Value("${aws.sns.arn}")
    private String AWS_SNS_ARN;

    public SnsService(SnsClient amazonSNSClient){
        this.amazonSNSClient = amazonSNSClient;
    }

    @Override
    public void sendMessage(String message, String destination) {
        PublishRequest publishRequest = PublishRequest
                .builder().message(message)
                .topicArn(AWS_SNS_ARN).subject("código de confirmação").build();
        PublishResponse response = amazonSNSClient.publish(publishRequest);
        System.out.println("mensagem enviada com ID: " + response.messageId());
    }
}
