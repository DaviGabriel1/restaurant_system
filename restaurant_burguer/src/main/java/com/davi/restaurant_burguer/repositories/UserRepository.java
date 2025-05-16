package com.davi.restaurant_burguer.repositories;

import com.davi.restaurant_burguer.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE u.deletedAt = null")
    public List<Users> findAll();
}
