package com.my_projects.news_app_v1.repository;

import com.my_projects.news_app_v1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    

}