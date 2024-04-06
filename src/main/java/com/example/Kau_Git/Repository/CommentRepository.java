package com.example.Kau_Git.Repository;

import com.example.Kau_Git.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
