package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.models.Users;
import com.davi.restaurant_burguer.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers() {
        return this.userRepository.findAll();
    }

    public void saveUser(Users user) {
        try{
            this.userRepository.save(user);
        }catch(Exception e){
            throw new RuntimeException("Error saving user",e);
        }
    }
}
