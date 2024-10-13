package com.example.spring_data_jpa.service.impl;

import com.example.spring_data_jpa.entity.User;
import com.example.spring_data_jpa.model.UserDTO;
import com.example.spring_data_jpa.repository.UserRepo;
import com.example.spring_data_jpa.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<UserDTO> getAll() {
        return userRepo.findAll()
                .stream().map(user -> objectMapper.convertValue(user, UserDTO.class)).toList();
    }

    @Override
    public UserDTO getUser(Long id) {
        return userRepo.findById(id)
                .map(user -> objectMapper.convertValue(user, UserDTO.class))
                .orElse(null);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        return objectMapper.convertValue(
                userRepo.save(User.builder().id(userDTO.getId()).name(userDTO.getName()).build())
                , UserDTO.class
        );
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDTO getUser(Long id, String name) {
        return userRepo.findByIdAndName(id, name)
                .map(user -> objectMapper.convertValue(user, UserDTO.class))
                .orElse(null);
    }
}