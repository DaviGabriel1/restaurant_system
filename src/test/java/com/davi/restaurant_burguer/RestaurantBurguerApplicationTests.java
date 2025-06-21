package com.davi.restaurant_burguer;

import com.davi.restaurant_burguer.interfaces.IStorageServiceAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class RestaurantBurguerApplicationTests {

	@MockitoBean
	private IStorageServiceAdapter storageServiceAdapter;

	@Test
	void contextLoads() {
	}

}
