package com.davi.restaurant_burguer.repositories;

import com.davi.restaurant_burguer.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    @Query("SELECT p FROM ProductImage p WHERE p.product.id = ?1 and p.deletedAt is null")
    List<ProductImage> findAllByProductId(Long id);
}