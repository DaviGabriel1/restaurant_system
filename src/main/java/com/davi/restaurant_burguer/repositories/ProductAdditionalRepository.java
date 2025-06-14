package com.davi.restaurant_burguer.repositories;

import com.davi.restaurant_burguer.models.ProductAdditional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductAdditionalRepository extends JpaRepository<ProductAdditional,Long> {
    ProductAdditional save(ProductAdditional productAdditional);

    @Query("SELECT pa from ProductAdditional pa where pa.productId=?1 and pa.deletedAt is null")
    List<ProductAdditional> findAll(Long productId);

    @Query("SELECT pa from ProductAdditional pa where pa.id = ?1 and pa.deletedAt is null")
    ProductAdditional findOne(Long productAdditionalId);

    List<ProductAdditional> findProductAdditionalByIdIn(Collection<Long> ids);
}
