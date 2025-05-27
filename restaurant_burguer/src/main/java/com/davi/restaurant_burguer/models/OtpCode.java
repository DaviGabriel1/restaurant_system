package com.davi.restaurant_burguer.models;

import com.davi.restaurant_burguer.utils.TimeUtils;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp_code")
public class OtpCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String phone;

    @Column
    private String code;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column
    private boolean active;

    public OtpCode() {
        this.expiresAt = TimeUtils.setTimeOut(5L);
        this.active = true;
    }

    public OtpCode(String phone, String code) {
        this.phone = phone;
        this.code = code;
        this.expiresAt = TimeUtils.setTimeOut(5L);
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "OtpCode{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", expiresAt=" + expiresAt +
                ", active=" + active +
                '}';
    }
}
