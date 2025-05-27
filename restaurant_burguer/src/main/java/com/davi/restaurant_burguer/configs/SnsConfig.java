package com.davi.restaurant_burguer.configs;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnsConfig {

    @Bean
    public SnsClient snsClient(@Value("${aws.access-key.id}") String accessKeyId, @Value("${aws.secret.access-key}") String accessSecretKey, @Value("${aws.s3.region}") String region) {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKeyId,accessSecretKey);
        return SnsClient
                .builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }
}
