package com.example.Kau_Git.repository;

import com.example.Kau_Git.entity.BannedContent;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface BannedContentRepository extends JpaRepository<BannedContent,Long> {
    BannedContent findById(long id);

}
