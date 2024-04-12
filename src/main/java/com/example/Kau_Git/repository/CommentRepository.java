package com.example.KauGI;

import com.example.Kau_Git.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Comment findByCommentId(long commentId);
}
