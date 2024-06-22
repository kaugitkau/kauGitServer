package com.example.Kau_Git.repository;

import com.example.Kau_Git.entity.Hashtag;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.PostingHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostingHashtagRepository extends JpaRepository<PostingHashtag, Long> {
    @Query("SELECT ph.hashtag FROM PostingHashtag ph WHERE ph.posting = :posting")
    List<Hashtag> findHashtagsByPosting(@Param("posting") Posting posting);

    @Query("SELECT ph.posting FROM PostingHashtag ph WHERE ph.hashtag.word = :hashtag")
    List<Posting> findPostingByHashtag(@Param("hashtag") String hashtag);
}
