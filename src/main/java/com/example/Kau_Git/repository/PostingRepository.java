package com.example.Kau_Git.repository;
import com.example.Kau_Git.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting,Long> {

    List<Posting> findAllByClassification(char c);

    List<Posting> findAllByWriter_UserIdAndClassification(Long userId, char c);
    Posting findByPostingId(Long postingId);

    Integer countByWriter_UserId(Long userId);

    @Query("SELECT SUM(p.recommendedCnt) FROM Posting p where p.writer.id = :userId")
    Integer sumRecommendedCntByUserId(@Param("userId") Long userId);

    List<Posting> findAllByTitle(String title);




}
