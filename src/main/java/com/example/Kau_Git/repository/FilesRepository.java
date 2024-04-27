package com.example.Kau_Git.repository;

import com.example.Kau_Git.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<Files, Long> {
}
