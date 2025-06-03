package com.davi.restaurant_burguer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class RestaurantBurguerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantBurguerApplication.class, args);
	}

}
