package com.example.spring_data_jpa.service;

import com.example.spring_data_jpa.model.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();

    UserDTO getUser(Long id);

    UserDTO addUser(UserDTO userDTO);

    void deleteUser(Long id);

    UserDTO getUser(Long id, String name);
}