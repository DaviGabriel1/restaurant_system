package com.davi.restaurant_burguer.repositories;

import com.davi.restaurant_burguer.models.OtpCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {

    @Query("SELECT o FROM OtpCode o WHERE o.phone = ?1 AND o.code = ?2 AND o.active = true AND o.expiresAt > ?3")
    OtpCode findOneValidCode(String phone, String code, LocalDateTime now);

    @Transactional
    @Modifying
    @Query("UPDATE OtpCode o SET o.active = false WHERE o.phone = ?1")
    void setActiveCodeToFalse(String phone);
}
