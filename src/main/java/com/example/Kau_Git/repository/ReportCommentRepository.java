package com.example.Kau_Git.repository;

import com.example.Kau_Git.entity.ReportComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCommentRepository extends JpaRepository<ReportComment, Long> {

    @Query("DELETE FROM ReportComment c WHERE c.comment.id = :commentId")
    void deleteByCommentId(@Param("commentId") Long commentId);

}
