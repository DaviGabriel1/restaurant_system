package com.davi.restaurant_burguer.repositories;

import com.davi.restaurant_burguer.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.uuid = ?1 and p.deletedAt is null")
    Product getByUuid(String uuid);

    @Query("SELECT p FROM Product p WHERE p.deletedAt is null")
    List<Product> findAll();
}
