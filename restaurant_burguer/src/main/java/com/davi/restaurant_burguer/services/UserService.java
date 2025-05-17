package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.dtos.users.ResponseUserDTO;
import com.davi.restaurant_burguer.mappers.UserMapper;
import com.davi.restaurant_burguer.models.Users;
import com.davi.restaurant_burguer.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<ResponseUserDTO> getAllUsers() {
        List<Users> users = this.userRepository.findAll();
        return this.userMapper.mapFindAll(users);
    }

    public ResponseUserDTO getUser(long id) {
        return userMapper.mapToResponseUserDTO(this.userRepository.findOne(id));
    }

    public Users getUserByLogin(String login) {
        return this.userRepository.findOneByLogin(login);
    }

    public void saveUser(Users user) {
        try{
            this.userRepository.save(user);
        }catch(Exception e){
            throw new RuntimeException("Error saving user",e);
        }
    }
}
