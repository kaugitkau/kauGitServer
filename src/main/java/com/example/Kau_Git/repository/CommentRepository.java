package com.example.Kau_Git.repository;

import com.example.Kau_Git.entity.Comment;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostingOrderByCreatedAtAsc(Posting posting);

    Integer countByPosting_PostingId(Long postId);

    Integer countByWriter_UserId(Long userId);

    Comment findByCommentId(Long commentId);

    //신고횟수가 특정 수 이상이면 가져오는 것
    List<Comment> findAllWithValueGreaterThan(@Param("warningCnt") int value);
}
