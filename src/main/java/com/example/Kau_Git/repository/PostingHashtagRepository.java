package com.example.Kau_Git.repository;

import com.example.Kau_Git.entity.Hashtag;
import com.example.Kau_Git.entity.PostingHashtag;
import com.example.Kau_Git.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostingHashtagRepository extends JpaRepository<PostingHashtag, Long> {
    @Query("SELECT ph.hashtag FROM PostingHashtag ph WHERE ph.posting = :posting")
    List<Hashtag> findHashtagsByPosting(@Param("posting") Posting posting);
}
