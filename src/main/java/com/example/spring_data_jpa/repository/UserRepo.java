package com.example.spring_data_jpa.repository;

import com.example.spring_data_jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByIdAndName(Long id, String name);
}