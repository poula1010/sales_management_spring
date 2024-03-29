package com.poula.sales_management.repository;

import com.poula.sales_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsernameOrEmail(String username,String email);
}
