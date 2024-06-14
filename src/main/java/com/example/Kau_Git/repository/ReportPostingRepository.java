package com.example.Kau_Git.repository;

import com.example.Kau_Git.entity.ReportPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportPostingRepository extends JpaRepository<ReportPosting, Long> {
    @Query("DELETE FROM ReportPosting p WHERE p.posting.id = :postingId")
    void deleteByPostingId(@Param("posting") Long postingId);
}
