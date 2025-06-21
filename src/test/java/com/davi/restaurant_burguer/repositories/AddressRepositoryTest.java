package com.davi.restaurant_burguer.repositories;

import com.davi.restaurant_burguer.models.Address;
import com.davi.restaurant_burguer.models.Users;
import jakarta.persistence.EntityManager;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ActiveProfiles("test")
class AddressRepositoryTest {
    @Autowired
    EntityManager entityManager;
    @Autowired
    AddressRepository addressRepository;

    @Test
    @DisplayName("should return the main address")
    void getMainAddressByUserIdCase1() {
        Address mainAddress = returnATestAddress(true);
        createUser(mainAddress.getUserId());
        createAddress(mainAddress);
        Optional<Address> address = this.addressRepository.getMainAddressByUserId(mainAddress.getUserId());

        assertThat(address.isPresent()).isTrue();
    }

    @Test
    @DisplayName("should return empty main address because principal is false")
    void getMainAddressByUserIdCase2() {
        Address address = returnATestAddress(false);
        Users user = address.getUserId();
        this.createUser(user);
        this.createAddress(address);
        Optional<Address> emptyAddress = this.addressRepository.getMainAddressByUserId(user);
        assertThat(emptyAddress.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("should return empty main address because isDeleted is true")
    void getMainAddressByUserIdCase3() {
        Address address = returnATestAddress(true);
        Users user = address.getUserId();
        address.setDeletedAt(OffsetDateTime.now());
        this.createUser(user);
        this.createAddress(address);
        Optional<Address> emptyAddress = this.addressRepository.getMainAddressByUserId(user);
        assertThat(emptyAddress.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("should throw ConstraintViolationException because user is null")
    void getMainAddressByUserIdCase4() {
        Address address = returnATestAddress(true);
        address.setUserId(null);
        assertThatThrownBy(() -> createAddress(address)).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("should return a address by userId and AddressId")
    void findByUserIdAndIdCase1() {
        Address address = returnATestAddress(false);
        Users user = address.getUserId();
        createUser(user);
        createAddress(address);
        Optional<Address> addressReturned = this.addressRepository.findByUserIdAndId(user,address.getId());
        assertThat(addressReturned.isPresent()).isTrue();
    }

    @Test
    @DisplayName("should throw a ConstraintViolationException by createAddress method because user is null")
    void findByUserIdAndIdCase2() {
        Address address = returnATestAddress(false);
        address.setUserId(null);
        assertThatThrownBy(() -> createAddress(address)).isInstanceOf(ConstraintViolationException.class);
    }

    Address returnATestAddress(boolean isPrincipal) {
        Users user = new Users("Test user",1,"+5511999999999");

        return new Address(user,
                "06690-104",
                "236",
                "via alfa",
                "Cotia",
                "SP",
                null,
                "Jardim Novo",
                isPrincipal);
    }

    void createAddress(Address address) {
        this.entityManager.persist(address);
    }

    void createUser(Users user) {
        this.entityManager.persist(user);
    }
}