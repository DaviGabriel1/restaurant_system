package com.davi.restaurant_burguer.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restaurant System")
                        .version("v1")
                        .description("Api to restaurant management with Spring + AWS + Docker + CI/CD")
                        //.termsOfService("fake-url")
                        .license(
                                new License()
                                        .name("fake license")
                                        .url("fake-url")
                        )
                );
    }
}
