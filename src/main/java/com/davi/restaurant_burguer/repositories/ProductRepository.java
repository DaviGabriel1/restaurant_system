package com.davi.restaurant_burguer.repositories;

import com.davi.restaurant_burguer.models.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = {"images"})
    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN p.images img where img.deletedAt is null and p.uuid = ?1 and p.deletedAt is null")
    Product getByUuid(String uuid);
    @EntityGraph(attributePaths = {"images"})
    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN p.images img where img.deletedAt is null and p.deletedAt is null")
    List<Product> findAll();

    Optional<List<Product>> findByIdIn(Collection<Long> ids);
}
