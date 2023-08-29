package com.example.chanel.repository;

import com.example.chanel.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface ChanelRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
