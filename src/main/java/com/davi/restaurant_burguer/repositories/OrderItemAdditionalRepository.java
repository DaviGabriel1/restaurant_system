package com.davi.restaurant_burguer.repositories;

import com.davi.restaurant_burguer.models.OrderItemAdditional;
import com.davi.restaurant_burguer.models.ProductAdditional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderItemAdditionalRepository extends JpaRepository<OrderItemAdditional,Long> {

    List<ProductAdditional> findOrderItemAdditionalByIdIn(Collection<Long> ids);
}
