package com.example.Kau_Git.repository;

import com.example.Kau_Git.entity.BannedPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannedPostingRepository extends JpaRepository<BannedPosting, Long> {
}
