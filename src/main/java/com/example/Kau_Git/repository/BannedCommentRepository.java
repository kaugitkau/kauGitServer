package com.example.Kau_Git.repository;

import com.example.Kau_Git.entity.BannedComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannedCommentRepository extends JpaRepository<BannedComment, Long> {

}
