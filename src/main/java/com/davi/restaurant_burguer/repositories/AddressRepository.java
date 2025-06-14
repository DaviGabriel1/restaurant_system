package com.davi.restaurant_burguer.repositories;

import com.davi.restaurant_burguer.models.Address;
import com.davi.restaurant_burguer.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a WHERE a.userId=?1 and a.principal=true and a.deletedAt is null")
    Optional<Address> getMainAddressByUserId(Users userId);
    
    Optional<Address> findByUserIdAndId(Users userId, Long id);
}
