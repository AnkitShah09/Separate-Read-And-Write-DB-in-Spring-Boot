package com.example.spring_data_jpa.controller;

import com.example.spring_data_jpa.model.UserDTO;
import com.example.spring_data_jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getUserByID(@PathVariable("id") Long id, @RequestParam(value = "name", required = false) String name) {
        if(StringUtils.hasText(name)) {
            return userService.getUser(id, name);
        }
        return userService.getUser(id);
    }

    @PostMapping
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUserByID(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
