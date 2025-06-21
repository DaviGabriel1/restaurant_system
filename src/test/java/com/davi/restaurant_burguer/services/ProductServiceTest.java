package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.dtos.products.ResponseProductDTO;
import com.davi.restaurant_burguer.enums.Category;
import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.interfaces.IStorageServiceAdapter;
import com.davi.restaurant_burguer.mappers.ProductMapper;
import com.davi.restaurant_burguer.models.Product;
import com.davi.restaurant_burguer.repositories.ProductImageRepository;
import com.davi.restaurant_burguer.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductImageRepository productImageRepository;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private IStorageServiceAdapter storageServiceAdapter;
    private static final String KEY_PRODUCT_IMAGE = "products-image/";
    @InjectMocks
    private AdditionalService additionalService;
    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("should return Product successfully when everything is OK")
    void getProductByIdCase1() {
        String uuid = UUID.randomUUID().toString();
        Product receiver = new Product(1L,
                uuid,
                "produto-teste",
                "descrição teste",
                new BigDecimal("9.99"),
                Category.OTHER.getId(),
                null,
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                null,
                true);

        when(productRepository.getByUuid(uuid)).thenReturn(receiver);
        
        productService.getProductByUuid(uuid);

        verify(this.productRepository, times(1)).getByUuid(uuid /*any()*/);
    }

    @Test
    @DisplayName("should throw NotFoundException when product is null")
    void getProductByIdCase2() {
        String uuid = UUID.randomUUID().toString();
        when(productRepository.getByUuid(any())).thenReturn(null);
        NotfoundException thrown = Assertions.assertThrows(NotfoundException.class,
                () -> {productService.getProductByUuid(uuid);});
        assertEquals("produto não encontrado", thrown.getMessage());
    }
}