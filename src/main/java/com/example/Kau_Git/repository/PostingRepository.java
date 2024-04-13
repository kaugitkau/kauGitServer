package com.example.Kau_Git.Repository;
import com.example.Kau_Git.Entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostingRepository extends JpaRepository<Posting,Long> {
    Posting findByPostId(long postId);

}
