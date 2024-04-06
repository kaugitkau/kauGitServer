package com.example.Kau_Git.Repository;

import com.example.Kau_Git.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
