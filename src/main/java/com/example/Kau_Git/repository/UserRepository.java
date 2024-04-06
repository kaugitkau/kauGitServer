package com.example.Kau_Git.repository;

import com.example.Kau_Git.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserId(long userId);

}
