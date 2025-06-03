package com.davi.restaurant_burguer.configs;

import com.davi.restaurant_burguer.dtos.sns.OrdersEmitDTO;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sns.model.Topic;

@Configuration
public class SnsConfig {

    @Value("${aws.sns.topic.orders-emit}")
    private String ordersEmitTopicArn;

    @Value("${aws.sns.send-message-arn}")
    private String sendMessageArn;

    @Bean
    public SnsClient snsClient(@Value("${aws.access-key.id}") String accessKeyId, @Value("${aws.secret.access-key}") String accessSecretKey, @Value("${aws.s3.region}") String region) {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKeyId,accessSecretKey);
        return SnsClient
                .builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }

    @Bean(name = "ordersEmitTopic")
    public OrdersEmitDTO getOrdersEmitTopicArn() {
        String[] arnEGroupId = ordersEmitTopicArn.split(" "); //separa o groupId pelo espaço em branco
        return new OrdersEmitDTO(arnEGroupId[0],arnEGroupId[1]);
    }

    @Bean(name = "sendMessageTopic")
    public Topic getSenderSMSTopicArn() {
        return Topic
                .builder()
                .topicArn(sendMessageArn)
                .build();
    }
}
