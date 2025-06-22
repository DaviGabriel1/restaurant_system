package com.davi.restaurant_burguer.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sns.SnsAsyncClient;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestConfig {
    @Bean
    public S3Client amazonS3() {
        return mock(S3Client.class);
    }

    @Bean
    public SnsAsyncClient amazonSNS() {
        return mock(SnsAsyncClient.class);
    }
}
