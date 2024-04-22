package com.example.Kau_Git.repository;
import com.example.Kau_Git.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting,Long> {
    Posting findByPostId(Long postId);

    List<Posting> findAllBYclassification(char c);

}
