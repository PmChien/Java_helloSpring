package com.example.helloSpring.repository;

import com.example.helloSpring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReponsitory extends JpaRepository<User,Long> {
    boolean existsByUserName(String userName);
}
