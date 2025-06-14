package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.models.Address;
import com.davi.restaurant_burguer.models.Users;
import com.davi.restaurant_burguer.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Optional<Address> getMainAddressByUserId(Users user) {
        return this.addressRepository.getMainAddressByUserId(user);
    }

    public Address getAddressById(Users user, Long addressId) {
        return this.addressRepository.findByUserIdAndId(user, addressId)
                .orElseThrow(() -> new NotfoundException("Endereço não encontrado"));
    }
}
